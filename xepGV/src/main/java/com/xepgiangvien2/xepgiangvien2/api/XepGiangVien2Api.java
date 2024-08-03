package com.xepgiangvien2.xepgiangvien2.api;

import com.xepgiangvien2.xepgiangvien2.entity.CaCoiThi;
import com.xepgiangvien2.xepgiangvien2.service.GiangVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/xeplichgv2")
@RequiredArgsConstructor
public class XepGiangVien2Api {

    private final GiangVienService giangVienService;

    @PostMapping("/sapxep")
    public ResponseEntity<?> sapXepGiangVien(@RequestBody CaCoiThi caDayDTO) {
        Map<String, Object> responseApi = new HashMap<>();
        try {
            responseApi.put("success", true);
            responseApi.put("message", "call api success");
            responseApi.put("data", giangVienService.sapXepGiangVien(caDayDTO.getCaDay(), caDayDTO.getGiangVien1(), caDayDTO.getTatCaGiangVien(), caDayDTO.getBackupGiangVien()));
        } catch (Exception e) {
            responseApi.put("success", false);
            responseApi.put("message", "call api Fail");
            responseApi.put("data", null);
        }
        return ResponseEntity.ok(responseApi);
    }
}
