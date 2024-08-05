package com.xepgiangvien2.xepgiangvien2.service.impl;

import com.xepgiangvien2.xepgiangvien2.service.GiangVienService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GiangVienServiceImpl implements GiangVienService {

    @Override
    public Map<String, List<String>> sapXepGiangVien(List<String> caThi, Map<String, List<String>> listGV1, List<String> tatCaGiangVien, List<String> backupGiangVien) {
        Map<String, List<String>> giangVien2 = new HashMap<>();

        for (int i = 0; i < caThi.size(); i++) {
            String ca = caThi.get(i);
            List<String> giangVienCoSan = new ArrayList<>(tatCaGiangVien);
            giangVienCoSan.removeAll(listGV1.get(ca));

            List<String> giangVienDuocChon = new ArrayList<>();
            if (i > 0) {
                String caTruoc = caThi.get(i - 1);
                for (String giangVien : listGV1.get(caTruoc)) {
                    if (giangVienCoSan.contains(giangVien) && giangVienDuocChon.size() < listGV1.get(ca).size()) {
                        giangVienDuocChon.add(giangVien);
                        giangVienCoSan.remove(giangVien);
                    }
                }
            }

            while (giangVienDuocChon.size() < listGV1.get(ca).size() && !giangVienCoSan.isEmpty()) {
                String giangVienDuocChonThem = giangVienCoSan.remove(0);
                giangVienDuocChon.add(giangVienDuocChonThem);
            }

            while (giangVienDuocChon.size() < listGV1.get(ca).size()) {
                giangVienDuocChon.add(null);
                System.out.println("Không đủ giảng viên có sẵn cho ca " + ca);
            }

            giangVien2.put(ca, giangVienDuocChon);
        }

        // Fill null slots with backup lecturers provided in the request
        fillNullSlots(giangVien2, backupGiangVien, tatCaGiangVien);

        return giangVien2;
    }

    private void fillNullSlots(Map<String, List<String>> giangVien2, List<String> backupGiangVien, List<String> tatCaGiangVien) {
        Random random = new Random();
        for (Map.Entry<String, List<String>> entry : giangVien2.entrySet()) {
            List<String> giangVienList = entry.getValue();
            for (int i = 0; i < giangVienList.size(); i++) {
                if (giangVienList.get(i) == null) {
                    String backup;
                    do {
                        backup = backupGiangVien.get(random.nextInt(backupGiangVien.size()));
                    } while (tatCaGiangVien.contains(backup) || giangVienList.contains(backup));
                    giangVienList.set(i, backup);
                }
            }
        }
    }
}