package com.xepgiangvien2.xepgiangvien2.service.impl;

import com.xepgiangvien2.xepgiangvien2.service.GiangVienService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GiangVienServiceImpl implements GiangVienService {

    @Override
    public Map<String, List<String>> sapXepGiangVien(List<String> caThi, Map<String, List<String>> listGV1, List<String> tatCaGiangVien, List<String> backupGiangVien) {
        Map<String, List<String>> giangVien2 = new HashMap<>();
        Set<String> tatCaGiangVienSet = new HashSet<>(tatCaGiangVien);

        for (String ca : caThi) {
            List<String> giangVienCoSan = new ArrayList<>(tatCaGiangVienSet);
            giangVienCoSan.removeAll(listGV1.getOrDefault(ca, Collections.emptyList()));

            List<String> giangVienDuocChon = new ArrayList<>();
            String caTruoc = caThi.indexOf(ca) > 0 ? caThi.get(caThi.indexOf(ca) - 1) : null;
            if (caTruoc != null) {
                for (String giangVien : listGV1.getOrDefault(caTruoc, Collections.emptyList())) {
                    if (giangVienCoSan.contains(giangVien) && giangVienDuocChon.size() < listGV1.getOrDefault(ca, Collections.emptyList()).size()) {
                        giangVienDuocChon.add(giangVien);
                        giangVienCoSan.remove(giangVien);
                    }
                }
            }

            while (giangVienDuocChon.size() < listGV1.getOrDefault(ca, Collections.emptyList()).size() && !giangVienCoSan.isEmpty()) {
                giangVienDuocChon.add(giangVienCoSan.remove(0));
            }

            while (giangVienDuocChon.size() < listGV1.getOrDefault(ca, Collections.emptyList()).size()) {
                giangVienDuocChon.add(null);
            }

            giangVien2.put(ca, giangVienDuocChon);
        }

        fillNullSlots(giangVien2, tatCaGiangVienSet, backupGiangVien);

        return giangVien2;
    }

    private void fillNullSlots(Map<String, List<String>> giangVien2, Set<String> tatCaGiangVienSet, List<String> backupGiangVien) {
        List<String> shuffledBackupGiangVien = new ArrayList<>(backupGiangVien);
        Collections.shuffle(shuffledBackupGiangVien);
        Iterator<String> backupIterator = shuffledBackupGiangVien.iterator();

        for (List<String> giangVienList : giangVien2.values()) {
            for (int i = 0; i < giangVienList.size(); i++) {
                if (giangVienList.get(i) == null) {
                    String backup;
                    do {
                        if (!backupIterator.hasNext()) {
                            backupIterator = shuffledBackupGiangVien.iterator();
                        }
                        backup = backupIterator.next();
                    } while (tatCaGiangVienSet.contains(backup) || giangVienList.contains(backup));
                    giangVienList.set(i, backup);
                }
            }
        }
    }
}