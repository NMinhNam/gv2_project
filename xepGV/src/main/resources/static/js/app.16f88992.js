(function(){"use strict";var t={4795:function(t,e,n){var a=n(5130),r=n(6768);function i(t,e){const n=(0,r.g2)("router-link"),a=(0,r.g2)("router-view");return(0,r.uX)(),(0,r.CE)(r.FK,null,[(0,r.Lk)("nav",null,[(0,r.bF)(n,{to:"/"},{default:(0,r.k6)((()=>[(0,r.eW)("Home")])),_:1}),(0,r.eW)(" | "),(0,r.bF)(n,{to:"/xepGiangVien"},{default:(0,r.k6)((()=>[(0,r.eW)("Xếp giảng viên 2")])),_:1})]),(0,r.bF)(a)],64)}var l=n(1241);const o={},s=(0,l.A)(o,[["render",i]]);var c=s,u=n(1387),g=n(4232);const d={class:"main"},p=(0,r.Lk)("div",{class:"row my-3"},[(0,r.Lk)("div",{class:"fs-1 text-center"}," DANH SÁCH GIẢNG VIÊN CHẤM THI ")],-1),h={class:"row d-flex justify-content-center"},f={class:"input-group mb-3 text-center w-50"},b={class:"row mb-3 mx-5"},k={class:"table table-bordered border-black"},v=(0,r.Lk)("thead",null,[(0,r.Lk)("tr",{class:"text-center"},[(0,r.Lk)("th",null,"Mã môn"),(0,r.Lk)("th",null,"Lớp"),(0,r.Lk)("th",null,"Ngày thi"),(0,r.Lk)("th",null,"Ca thi"),(0,r.Lk)("th",null,"Phòng thi"),(0,r.Lk)("th",null,"Giảng viên 1"),(0,r.Lk)("th",null,"Giảng viên 2")])],-1),L={class:"bg-transparent"},m={class:"bg-transparent"},y={class:"bg-transparent"},_={class:"bg-transparent"},P={class:"bg-transparent"},x={class:"bg-transparent"},w={class:"bg-transparent"},T={class:"border-0"},V={colspan:"7",class:"bg-transparent border-0 p-4 text-center"},C=["disabled"],S=(0,r.Lk)("i",{class:"fa-solid fa-angles-left"},null,-1),F=[S],H=["disabled"],O=(0,r.Lk)("i",{class:"fa-solid fa-angle-left"},null,-1),D=[O],j={class:"col mx-3 h-100 d-inline"},E=["disabled"],M=(0,r.Lk)("i",{class:"fa-solid fa-angle-right"},null,-1),A=[M],W=["disabled"],G=(0,r.Lk)("i",{class:"fa-solid fa-angles-right"},null,-1),N=[G];function I(t,e,n,a,i,l){return(0,r.uX)(),(0,r.CE)("div",d,[p,(0,r.Lk)("div",h,[(0,r.Lk)("div",f,[(0,r.Lk)("input",{type:"file",class:"form-control",id:"fileUpload",onChange:e[0]||(e[0]=(...t)=>l.inputFile_change&&l.inputFile_change(...t))},null,32),(0,r.Lk)("button",{class:"btn btn-primary",onClick:e[1]||(e[1]=(...t)=>l.btnFilter_click&&l.btnFilter_click(...t))},"Sắp xếp")])]),(0,r.Lk)("div",b,[(0,r.Lk)("table",k,[v,(0,r.Lk)("tbody",null,[((0,r.uX)(!0),(0,r.CE)(r.FK,null,(0,r.pI)(l.displayed,((t,e)=>((0,r.uX)(),(0,r.CE)("tr",{key:e},[(0,r.Lk)("td",L,(0,g.v_)(t.maMon),1),(0,r.Lk)("td",m,(0,g.v_)(t.lop),1),(0,r.Lk)("td",y,(0,g.v_)(t.ngayThi),1),(0,r.Lk)("td",_,(0,g.v_)(t.caThi),1),(0,r.Lk)("td",P,(0,g.v_)(t.phongThi),1),(0,r.Lk)("td",x,(0,g.v_)(t.giangVien1),1),(0,r.Lk)("td",w,(0,g.v_)(t.giangVien2),1)])))),128))]),(0,r.Lk)("tfoot",null,[(0,r.Lk)("tr",T,[(0,r.Lk)("td",V,[(0,r.Lk)("button",{type:"button",class:"btn border-black me-1",onClick:e[2]||(e[2]=(...t)=>l.btnFirst_click&&l.btnFirst_click(...t)),disabled:1==i.page.currentPage},F,8,C),(0,r.Lk)("button",{type:"button",class:"btn border-black",onClick:e[3]||(e[3]=(...t)=>l.btnPrev_click&&l.btnPrev_click(...t)),disabled:1==i.page.currentPage},D,8,H),(0,r.Lk)("div",j,(0,g.v_)(i.page.currentPage)+"/"+(0,g.v_)(l.totalPages),1),(0,r.Lk)("button",{type:"button",class:"btn border-black me-1",onClick:e[4]||(e[4]=(...t)=>l.btnNext_click&&l.btnNext_click(...t)),disabled:i.page.currentPage==l.totalPages},A,8,E),(0,r.Lk)("button",{type:"button",class:"btn border-black",onClick:e[5]||(e[5]=(...t)=>l.btnLast_click&&l.btnLast_click(...t)),disabled:i.page.currentPage==l.totalPages},N,8,W)])])])])])])}n(4114),n(6573),n(8100),n(7936),n(7467),n(4732),n(9577),n(3375),n(9225),n(3972),n(9209),n(5714),n(7561),n(6197);var X=n(6606),K=n(4373),U={name:"MainView",props:{},data(){return{filteredDataWithoutHeader:[],page:{currentPage:1,itemsPerPage:15}}},computed:{displayed(){let t=(this.page.currentPage-1)*this.page.itemsPerPage;return this.filteredDataWithoutHeader.slice(t,t+this.page.itemsPerPage)},totalPages(){return Math.ceil(this.filteredDataWithoutHeader.length/this.page.itemsPerPage)}},methods:{inputFile_change(){let t=document.getElementById("fileUpload");if(t.files.length>0){let e=t.files[0],n=new FileReader;n.onload=t=>{const e=new Uint8Array(t.target.result),n=X.read(e,{type:"array"}),a=n.SheetNames[0],r=n.Sheets[a],i=X.utils.sheet_to_json(r,{header:1});let l=i.slice(1).map((t=>({maMon:t[1],lop:t[2],ngayThi:this.parseExcelDate(t[3]),caThi:t[4],phongThi:t[7],giangVien1:t[9],giangVien2:t[10]||void 0})));this.filteredDataWithoutHeader=l.filter((t=>t.maMon&&t.lop&&t.ngayThi&&t.caThi&&t.phongThi&&t.giangVien1&&t.giangVien2))},n.readAsArrayBuffer(e)}else console.error("Không có tệp nào được chọn.")},parseExcelDate(t){const e=new Date(Math.round(86400*(t-25569)*1e3)),n=e.getDate().toString().padStart(2,"0"),a=(e.getMonth()+1).toString().padStart(2,"0"),r=e.getFullYear().toString();return`${n}-${a}-${r}`},btnFirst_click(){this.page.currentPage=1},btnPrev_click(){this.page.currentPage--},btnNext_click(){this.page.currentPage++},btnLast_click(){this.page.currentPage=this.totalPages},async btnFilter_click(){try{let t=new Set,e={1:[],2:[],3:[],4:[],5:[],6:[]},n=new Set;this.filteredDataWithoutHeader.forEach((a=>{t.add(a.caThi),e[a.caThi].includes(a.giangVien1)||e[a.caThi].push(a.giangVien1),n.add(a.giangVien1)})),t=Array.from(t),n=Array.from(n);for(let o in e)0===e[o].length&&delete e[o];let a={caDay:t,giangVien1:e,tatCaGiangVien:n};console.log(a);const r=JSON.stringify(a),i=await K.A.post("/api/v1/xeplichgv2/sapxep",r,{headers:{"Content-Type":"application/json"}});console.log(i.data);const l=i.data.data;this.updateGiangVien2(l)}catch(t){console.error(t)}},updateGiangVien2(t){let e={};for(let n in t)e[n]=0;this.filteredDataWithoutHeader=this.filteredDataWithoutHeader.map((n=>{const a=n.caThi;return t[a]&&e[a]<t[a].length&&(n.giangVien2=t[a][e[a]],e[a]++),n}))}}};const $=(0,l.A)(U,[["render",I]]);var B=$;const J=t=>((0,r.Qi)("data-v-322fa3a7"),t=t(),(0,r.jt)(),t),Q={class:"hello"},R=J((()=>(0,r.Lk)("h1",null,"Hello",-1))),Y=[R];function q(t,e,n,a,i,l){return(0,r.uX)(),(0,r.CE)("div",Q,Y)}var z={name:"HelloWorld",props:{msg:String}};const Z=(0,l.A)(z,[["render",q],["__scopeId","data-v-322fa3a7"]]);var tt=Z;const et=[{path:"/",name:"hello",component:tt},{path:"/xepGiangVien",name:"xepGiangVien",component:B}],nt=(0,u.aE)({history:(0,u.LA)("/"),routes:et});var at=nt;(0,a.Ef)(c).use(at).mount("#app")}},e={};function n(a){var r=e[a];if(void 0!==r)return r.exports;var i=e[a]={exports:{}};return t[a].call(i.exports,i,i.exports,n),i.exports}n.m=t,function(){var t=[];n.O=function(e,a,r,i){if(!a){var l=1/0;for(u=0;u<t.length;u++){a=t[u][0],r=t[u][1],i=t[u][2];for(var o=!0,s=0;s<a.length;s++)(!1&i||l>=i)&&Object.keys(n.O).every((function(t){return n.O[t](a[s])}))?a.splice(s--,1):(o=!1,i<l&&(l=i));if(o){t.splice(u--,1);var c=r();void 0!==c&&(e=c)}}return e}i=i||0;for(var u=t.length;u>0&&t[u-1][2]>i;u--)t[u]=t[u-1];t[u]=[a,r,i]}}(),function(){n.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return n.d(e,{a:e}),e}}(),function(){n.d=function(t,e){for(var a in e)n.o(e,a)&&!n.o(t,a)&&Object.defineProperty(t,a,{enumerable:!0,get:e[a]})}}(),function(){n.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(t){if("object"===typeof window)return window}}()}(),function(){n.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)}}(),function(){n.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})}}(),function(){var t={524:0};n.O.j=function(e){return 0===t[e]};var e=function(e,a){var r,i,l=a[0],o=a[1],s=a[2],c=0;if(l.some((function(e){return 0!==t[e]}))){for(r in o)n.o(o,r)&&(n.m[r]=o[r]);if(s)var u=s(n)}for(e&&e(a);c<l.length;c++)i=l[c],n.o(t,i)&&t[i]&&t[i][0](),t[i]=0;return n.O(u)},a=self["webpackChunkfe"]=self["webpackChunkfe"]||[];a.forEach(e.bind(null,0)),a.push=e.bind(null,a.push.bind(a))}();var a=n.O(void 0,[504],(function(){return n(4795)}));a=n.O(a)})();
//# sourceMappingURL=app.16f88992.js.map