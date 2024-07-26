package com.xepgiangvien2.xepgiangvien2.service;

import com.xepgiangvien2.xepgiangvien2.dto.request.ThongTinLichThiRequest;
import com.xepgiangvien2.xepgiangvien2.dto.response.ThongTinLichThiResponse;

import java.util.List;
import java.util.Map;

public interface GiangVienService {
    Map<String, List<String>> sapXepGiangVien(List<String> caDay, Map<String, List<String>> giangVien1, List<String> tatCaGiangVien);
}