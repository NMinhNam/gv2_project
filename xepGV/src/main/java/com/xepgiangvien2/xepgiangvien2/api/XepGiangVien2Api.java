package com.xepgiangvien2.xepgiangvien2.api;

import com.xepgiangvien2.xepgiangvien2.dto.request.ThongTinLichThiRequest;
import com.xepgiangvien2.xepgiangvien2.service.XepLichGiangVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/xeplichgv2")
@RequiredArgsConstructor
public class XepGiangVien2Api {

    final XepLichGiangVienService xepLichGiangVienService;

    @PostMapping("/postLichGiangVien2")
    public ResponseEntity<?> doPostLichGiangVien2(@RequestBody ThongTinLichThiRequest request) {
        Map<String, Object> resultApi = new HashMap<>();
        try {
            resultApi.put("data", xepLichGiangVienService.xepLichGiangVien(request));
            resultApi.put("success", true);
            resultApi.put("message", "");
        } catch (Exception e) {
            resultApi.put("data", null);
            resultApi.put("success", false);
            resultApi.put("message", "");
        }
        return ResponseEntity.ok(resultApi);
    }
}
