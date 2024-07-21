import java.util.*;

public class XepLichGiangVien2 {

    public static Map<String, List<String>> sapXepGiangVien(List<String> caDay, Map<String, List<String>> giangVien1, List<String> tatCaGiangVien) {
        Map<String, List<String>> giangVien2 = new HashMap<>();

        for (int i = 0; i < caDay.size(); i++) {
            String ca = caDay.get(i);
            List<String> giangVienCoSan = new ArrayList<>(tatCaGiangVien);
            giangVienCoSan.removeAll(giangVien1.get(ca));

            List<String> giangVienDuocChon = new ArrayList<>();
            if (i > 0) {
                String caTruoc = caDay.get(i - 1);
                for (String giangVien : giangVien1.get(caTruoc)) {
                    if (giangVienCoSan.contains(giangVien) && giangVienDuocChon.size() < giangVien1.get(ca).size()) {
                        giangVienDuocChon.add(giangVien);
                        giangVienCoSan.remove(giangVien);
                    }
                }
            }

            while (giangVienDuocChon.size() < giangVien1.get(ca).size() && !giangVienCoSan.isEmpty()) {
                String giangVienDuocChonThem = giangVienCoSan.remove(0);
                giangVienDuocChon.add(giangVienDuocChonThem);
            }

            // Nếu không đủ giảng viên có sẵn, thêm null và in ra cảnh báo
            while (giangVienDuocChon.size() < giangVien1.get(ca).size()) {
                giangVienDuocChon.add(null);
                System.out.println("Không đủ giảng viên có sẵn cho ca " + ca);
            }

            giangVien2.put(ca, giangVienDuocChon);
        }

        return giangVien2;
    }

    public static void main(String[] args) {
        List<String> lichCa = Arrays.asList("Ca1", "Ca2", "Ca3", "Ca4", "Ca5", "Ca6");

        Map<String, List<String>> giangVien1 = new HashMap<>();
        giangVien1.put("Ca1", Arrays.asList("Ha", "Nam", "Dat", "Giang"));
        giangVien1.put("Ca2", Arrays.asList("Quynh", "Ha", "Thanh"));
        giangVien1.put("Ca3", Arrays.asList("Duy", "Loc"));
        giangVien1.put("Ca4", Arrays.asList("Hoang", "My"));
        giangVien1.put("Ca5", Arrays.asList("Quoc", "Tung"));
        giangVien1.put("Ca6", Arrays.asList("G.Minh", "H.Nam"));

        List<String> tatCaGiangVien = Arrays.asList("Ha", "Nam", "Dat", "Giang", "Quynh", "Thanh", "Duy",
                "Loc", "Hoang", "My", "Quoc", "Tung", "G.Minh", "H.Nam");

        try {
            Map<String, List<String>> giangVien2 = sapXepGiangVien(lichCa, giangVien1, tatCaGiangVien);
            for (Map.Entry<String, List<String>> entry : giangVien2.entrySet()) {
                System.out.println("Ca: " + entry.getKey() + ", Giảng Viên 2: " + entry.getValue());
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
