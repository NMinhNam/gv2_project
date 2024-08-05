<script>
import * as XLSX from "xlsx/dist/xlsx.full.min";
import axios from "axios";

export default {
  name: "MainView",
  props: {},
  data() {
    return {
      filteredDataWithoutHeader: [],
      page: {
        currentPage: 1,
        itemsPerPage: 15,
      },
    };
  },
  computed: {
    displayed() {
      let start = (this.page.currentPage - 1) * this.page.itemsPerPage;
      return this.filteredDataWithoutHeader.slice(start, start + this.page.itemsPerPage);
    },
    totalPages() {
      return Math.ceil(this.filteredDataWithoutHeader.length / this.page.itemsPerPage);
    },
  },
  methods: {
    inputFile_change() {
      let fileUpload = document.getElementById("fileUpload");

      if (fileUpload.files.length > 0) {
        let file = fileUpload.files[0];
        let reader = new FileReader();

        reader.onload = (e) => {
          const data = new Uint8Array(e.target.result);
          const workbook = XLSX.read(data, { type: "array" });

          const firstSheetName = workbook.SheetNames[0];
          const worksheet = workbook.Sheets[firstSheetName];
          const jsonData = XLSX.utils.sheet_to_json(worksheet, { header: 1 });

          let dataWithOutHeader = jsonData.slice(1).map((row) => ({
            maMon: row[1],
            lop: row[2],
            ngayThi: this.parseExcelDate(row[3]),
            caThi: row[4],
            phongThi: row[7],
            giangVien1: row[9],
            giangVien2: row[10] || null,
          }));
          this.filteredDataWithoutHeader = dataWithOutHeader.filter((row) => {
            return (
              row.maMon && row.lop && row.ngayThi && row.caThi && row.phongThi && row.giangVien1
            );
          });
        };

        reader.readAsArrayBuffer(file);
      } else {
        console.error("Không có tệp nào được chọn.");
      }
    },
    parseExcelDate(serial) {
      const date = new Date(Math.round((serial - 25569) * 86400 * 1000));
      const day = date.getDate().toString().padStart(2, "0");
      const month = (date.getMonth() + 1).toString().padStart(2, "0");
      const year = date.getFullYear().toString();

      return `${day}-${month}-${year}`;
    },
    btnFirst_click() {
      this.page.currentPage = 1;
    },
    btnPrev_click() {
      this.page.currentPage--;
    },
    btnNext_click() {
      this.page.currentPage++;
    },
    btnLast_click() {
      this.page.currentPage = this.totalPages;
    },
    async btnFilter_click() {
      try {
        let caDay = new Set();
        let giangVien = {
          1: [],
          2: [],
          3: [],
          4: [],
          5: [],
          6: [],
        };
        let allGiangVien = new Set();
        this.filteredDataWithoutHeader.forEach((e) => {
          caDay.add(e.caThi);
          if (!giangVien[e.caThi].includes(e.giangVien1)) {
            giangVien[e.caThi].push(e.giangVien1);
          }
          allGiangVien.add(e.giangVien1);
        });
        caDay = Array.from(caDay);
        allGiangVien = Array.from(allGiangVien);
        for (let key in giangVien) {
          if (giangVien[key].length === 0) {
            delete giangVien[key];
          }
        }
        let result = {
          caDay: caDay,
          giangVien1: giangVien,
          tatCaGiangVien: allGiangVien,
          backupGiangVien: allGiangVien,
        };
        console.log(JSON.stringify(result));

        const jsonResult = JSON.stringify(result);

        const response = await axios.post(`/api/v1/xeplichgv2/sapxep`, jsonResult, {
          headers: {
            "Content-Type": "application/json",
          },
        });
        console.log(response.data);

        const giangVien2Result = response.data.data;

        // Cập nhật dữ liệu
        this.updateGiangVien2(giangVien2Result);
      } catch (error) {
        console.error(error);
      }
    },
    updateGiangVien2(giangVien2Result) {
      let giangVien2Index = {};

      for (let ca in giangVien2Result) {
        giangVien2Index[ca] = 0;
      }

      this.filteredDataWithoutHeader = this.filteredDataWithoutHeader.map((item) => {
        const ca = item.caThi;
        if (giangVien2Result[ca] && giangVien2Index[ca] < giangVien2Result[ca].length) {
          item.giangVien2 = giangVien2Result[ca][giangVien2Index[ca]];
          giangVien2Index[ca]++;
        }
        return item;
      });
    },
    btnDownloadFile_click() {
      // Tạo một workbook mới
      const wb = XLSX.utils.book_new();

      // Tạo header cho worksheet
      const wsData = [
        ["Mã môn", "Lớp", "Ngày thi", "Ca thi", "Phòng thi", "Giảng viên 1", "Giảng viên 2"]
      ];

      // Thêm dữ liệu vào wsData
      this.filteredDataWithoutHeader.forEach(item => {
        wsData.push([
          item.maMon,
          item.lop,
          item.ngayThi,
          item.caThi,
          item.phongThi,
          item.giangVien1,
          item.giangVien2 || "Chưa có"
        ]);
      });

      // Tạo một worksheet từ dữ liệu
      const ws = XLSX.utils.aoa_to_sheet(wsData);

      // Thêm worksheet vào workbook
      XLSX.utils.book_append_sheet(wb, ws, "Danh sách giảng viên");

      // Tạo một blob từ workbook
      const wbout = XLSX.write(wb, { bookType: 'xlsx', type: 'binary' });

      // Chuyển đổi blob thành Uint8Array
      const buf = new ArrayBuffer(wbout.length);
      const view = new Uint8Array(buf);
      for (let i = 0; i < wbout.length; i++) view[i] = wbout.charCodeAt(i) & 0xFF;

      // Tạo Blob từ ArrayBuffer
      const blob = new Blob([buf], { type: 'application/octet-stream' });

      // Tạo URL cho blob
      const url = window.URL.createObjectURL(blob);

      // Tạo một thẻ a tạm thời để tải xuống
      const a = document.createElement('a');
      a.href = url;
      a.download = 'danh_sach_giang_vien.xlsx';

      // Kích hoạt sự kiện click để tải xuống
      a.click();

      // Giải phóng URL object
      window.URL.revokeObjectURL(url);
    }
  },
};
</script>

<template>
  <div class="main">
    <div class="row my-3">
      <div class="fs-1 text-center">DANH SÁCH GIẢNG VIÊN CHẤM THI</div>
    </div>
    <div class="row mb-3 d-flex">
      <div class="col-2">
        <div class="col mb-3">
          <label for="fileUploadAllGV" class="form-label">Danh sách giảng viên bộ môn</label>
        </div>
        <div class="col mb-3">
          <label for="fileUpload" class="form-label">Danh sách coi thi</label>
        </div>
      </div>
      <div class="col-7">
        <div class="col-6 mb-3">
          <input type="file" class="form-control" id="fileUploadAllGV" @change="inputFile_AllGV" />
        </div>
        <div class="col-6 mb-3">
          <input type="file" class="form-control" id="fileUpload" @change="inputFile_change" />
        </div>
      </div>
      <div class="col">
        <button class="btn btn-primary me-2" @click="btnFilter_click">Sắp xếp giảng viên</button>
        <button class="btn btn-success" @click="btnDownloadFile_click">Tải xuống file đã sắp xếp</button>
      </div>
    </div>
    <div class="row mb-3 mx-5">
      <table class="table table-bordered border-black">
        <thead>
          <tr class="text-center">
            <th>Mã môn</th>
            <th>Lớp</th>
            <th>Ngày thi</th>
            <th>Ca thi</th>
            <th>Phòng thi</th>
            <th>Giảng viên 1</th>
            <th>Giảng viên 2</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in displayed" :key="index">
            <td class="bg-transparent">{{ item.maMon }}</td>
            <td class="bg-transparent">{{ item.lop }}</td>
            <td class="bg-transparent">{{ item.ngayThi }}</td>
            <td class="bg-transparent">{{ item.caThi }}</td>
            <td class="bg-transparent">{{ item.phongThi }}</td>
            <td class="bg-transparent">{{ item.giangVien1 }}</td>
            <td class="bg-transparent">{{ item.giangVien2 || "Chưa có" }}</td>
          </tr>
        </tbody>
        <tfoot>
          <tr class="border-0">
            <td colspan="7" class="bg-transparent border-0 p-4 text-center">
              <button
                type="button"
                class="btn border-black me-1"
                @click="btnFirst_click"
                :disabled="page.currentPage == 1"
              >
                <i class="fa-solid fa-angles-left"></i>
              </button>
              <button
                type="button"
                class="btn border-black"
                @click="btnPrev_click"
                :disabled="page.currentPage == 1"
              >
                <i class="fa-solid fa-angle-left"></i>
              </button>
              <div class="col mx-3 h-100 d-inline">{{ page.currentPage }}/{{ totalPages }}</div>
              <button
                type="button"
                class="btn border-black me-1"
                @click="btnNext_click"
                :disabled="page.currentPage == totalPages"
              >
                <i class="fa-solid fa-angle-right"></i>
              </button>
              <button
                type="button"
                class="btn border-black"
                @click="btnLast_click"
                :disabled="page.currentPage == totalPages"
              >
                <i class="fa-solid fa-angles-right"></i>
              </button>
            </td>
          </tr>
        </tfoot>
      </table>
    </div>
  </div>
</template>

<style scoped></style>
