package com.xepgiangvien2.xepgiangvien2.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ThongTinLichThiRequest {
    private String maMon;
    private String lop;
    private String ngayThi;
    private String caThi;
    private String phongThi;
    private List<String> giangVien1;
    private List<String> tatCaGiangVien;
    private Map<String, Map<String, List<String>>> lichThi;
    private Map<String, List<String>> lichTrinh;
}
