package com.xepgiangvien2.xepgiangvien2.service.impl;

import com.xepgiangvien2.xepgiangvien2.dto.request.ThongTinLichThiRequest;
import com.xepgiangvien2.xepgiangvien2.dto.response.ThongTinLichThiResponse;
import com.xepgiangvien2.xepgiangvien2.service.XepLichGiangVienService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class XepLichGiangVienServiceImpl implements XepLichGiangVienService {
    @Override
    public ThongTinLichThiResponse xepLichGiangVien(ThongTinLichThiRequest request) {
        List<String> tatCaGiangVien = request.getTatCaGiangVien();
        List<String> giangVien1 = request.getGiangVien1();
        Map<String, Map<String, List<String>>> lichThi = request.getLichThi();
        String ngayThi = request.getNgayThi();
        String caThi = request.getCaThi();

        List<String> giangVien2 = sapXepGiangVien2(ngayThi, caThi, giangVien1, tatCaGiangVien, lichThi);

        ThongTinLichThiResponse response = new ThongTinLichThiResponse();
        response.setGiangVien2(giangVien2);
        return response;
    }

    private List<String> sapXepGiangVien2(String ngayThi, String caThi, List<String> giangVien1,
                                          List<String> tatCaGiangVien, Map<String, Map<String, List<String>>> lichThi) {
        List<String> giangVien2 = new ArrayList<>();

        // Loại bỏ giảng viên 1 khỏi danh sách tất cả giảng viên để chọn giảng viên 2
        List<String> availableGiangVien = new ArrayList<>(tatCaGiangVien);
        availableGiangVien.removeAll(giangVien1);

        if (lichThi.containsKey(ngayThi)) {
            Map<String, List<String>> caThiTrongNgay = lichThi.get(ngayThi);
            if (caThiTrongNgay.size() > 1) {
                // Trường hợp có nhiều giảng viên 1 trong ngày
                for (String ca : caThiTrongNgay.keySet()) {
                    if (!ca.equals(caThi)) {
                        for (String gv : caThiTrongNgay.get(ca)) {
                            if (availableGiangVien.contains(gv)) {
                                giangVien2.add(gv);
                                availableGiangVien.remove(gv);
                                break;
                            }
                        }
                    }
                    if (!giangVien2.isEmpty()) break;
                }
            } else {
                // Trường hợp chỉ có một giảng viên 1 trong ngày
                String nextDay = findNextAvailableDay(lichThi, ngayThi);
                if (nextDay != null && lichThi.get(nextDay) != null) {
                    for (String nextCa : lichThi.get(nextDay).keySet()) {
                        for (String gv : lichThi.get(nextDay).get(nextCa)) {
                            if (availableGiangVien.contains(gv)) {
                                giangVien2.add(gv);
                                availableGiangVien.remove(gv);
                                break;
                            }
                        }
                        if (!giangVien2.isEmpty()) break;
                    }
                }
            }
        }

        // Đảm bảo số lượng giảng viên 2 tương ứng với giảng viên 1
        while (giangVien2.size() < giangVien1.size()) {
            if (!availableGiangVien.isEmpty()) {
                giangVien2.add(availableGiangVien.remove(0));
            } else {
                giangVien2.add(null);
            }
        }

        return giangVien2;
    }

    private String findNextAvailableDay(Map<String, Map<String, List<String>>> lichThi, String currentDay) {
        // Tìm ngày kế tiếp gần nhất có sẵn giảng viên
        List<String> days = new ArrayList<>(lichThi.keySet());
        Collections.sort(days); // Giả định ngày thi được sắp xếp theo thứ tự
        for (String day : days) {
            if (day.compareTo(currentDay) > 0) {
                return day;
            }
        }
        return null;
    }
}
