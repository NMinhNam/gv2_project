<script>
import * as XLSX from 'xlsx/dist/xlsx.full.min';
export default {
  name: 'MainView',
  props: {
  },
  data() {
    return {
      filteredDataWithoutHeader: [],
      page: {
        currentPage: 1,
        itemsPerPage: 15
      }
    };
  },
  computed: {
    displayed() {
      let start = (this.page.currentPage - 1) * this.page.itemsPerPage
      return this.filteredDataWithoutHeader.slice(start, start + this.page.itemsPerPage)
    },
    totalPages() {
      return Math.ceil(this.filteredDataWithoutHeader.length / this.page.itemsPerPage)
    }
  },
  methods: {
    inputFile_change() {
      let fileUpload = document.getElementById('fileUpload');

      if (fileUpload.files.length > 0) {
        let file = fileUpload.files[0];
        let reader = new FileReader();

        reader.onload = (e) => {
          const data = new Uint8Array(e.target.result);
          const workbook = XLSX.read(data, { type: 'array' });

          const firstSheetName = workbook.SheetNames[0];
          const worksheet = workbook.Sheets[firstSheetName];
          const jsonData = XLSX.utils.sheet_to_json(worksheet, { header: 1 });

          let dataWithOutHeader = jsonData.slice(1)
              .map(row => ({
                maMon: row[1],
                lop: row[2],
                ngayThi: this.parseExcelDate(row[3]),
                caThi: row[4],
                phongThi: row[7],
                giangVien1: row[9]
              }));
          this.filteredDataWithoutHeader = dataWithOutHeader.filter(row => {
            return row.maMon && row.lop && row.ngayThi && row.caThi && row.phongThi && row.giangVien1
          });

        };

        reader.readAsArrayBuffer(file);
      } else {
        console.error('Không có tệp nào được chọn.');
      }
    },
    parseExcelDate(serial) {
      const date = new Date(Math.round((serial - 25569) * 86400 * 1000));
      const day = date.getDate().toString().padStart(2, '0');
      const month = (date.getMonth() + 1).toString().padStart(2, '0');
      const year = date.getFullYear().toString();

      return `${day}-${month}-${year}`;
    },
    btnFirst_click() {
      this.page.currentPage = 1
    },
    btnPrev_click() {
      this.page.currentPage--
    },
    btnNext_click() {
      this.page.currentPage++
    },
    btnLast_click() {
      this.page.currentPage = this.totalPages
    }
  }
}
</script>

<template>
  <div class="main">
    <div class="row my-3">
      <div class="fs-1 text-center">
        DANH SÁCH GIẢNG VIÊN CHẤM THI
      </div>
    </div>
    <div class="row d-flex justify-content-center">
      <div class="input-group mb-3 text-center w-50">
        <input type="file" class="form-control" id="fileUpload" @change="inputFile_change">
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
        </tr>
        </tbody>
        <tfoot>
        <tr class="border-0">
          <td colspan="6" class="bg-transparent border-0 p-4 text-center">
            <button type="button" class="btn border-black me-1" @click="btnFirst_click"
                    :disabled="page.currentPage == 1"><i class="fa-solid fa-angles-left"></i></button>
            <button type="button" class="btn border-black" @click="btnPrev_click"
                    :disabled="page.currentPage == 1"><i class="fa-solid fa-angle-left"></i></button>
            <div class="col mx-3 h-100 d-inline">{{ page.currentPage }}/{{ totalPages }}</div>
            <button type="button" class="btn border-black me-1" @click="btnNext_click"
                    :disabled="page.currentPage == totalPages"><i class="fa-solid fa-angle-right"></i></button>
            <button type="button" class="btn border-black" @click="btnLast_click"
                    :disabled="page.currentPage == totalPages"><i class="fa-solid fa-angles-right"></i></button>
          </td>
        </tr>
        </tfoot>
      </table>
    </div>
  </div>
</template>

<style scoped>

</style>