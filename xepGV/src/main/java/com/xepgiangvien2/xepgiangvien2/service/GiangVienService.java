package com.xepgiangvien2.xepgiangvien2.service;

import java.util.List;
import java.util.Map;

public interface GiangVienService {
    Map<String, List<String>> sapXepGiangVien(List<String> caThi, Map<String, List<String>> listGV1, List<String> tatCaGiangVien, List<String> backupGiangVien);
}