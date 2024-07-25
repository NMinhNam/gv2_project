package com.xepgiangvien2.xepgiangvien2.service;

import com.xepgiangvien2.xepgiangvien2.dto.request.ThongTinLichThiRequest;
import com.xepgiangvien2.xepgiangvien2.dto.response.ThongTinLichThiResponse;

public interface XepLichGiangVienService {
    /**
     *
     * @param request
     * @return
     */
    ThongTinLichThiResponse xepLichGiangVien(ThongTinLichThiRequest request);
}
