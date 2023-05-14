"use strict";(self["webpackChunklibrary_system"]=self["webpackChunklibrary_system"]||[]).push([[965],{8965:function(t,o,e){e.r(o),e.d(o,{default:function(){return b}});e(7658);var a=e(3396),r=e(9242);const s=(0,a._)("h1",null,"图书管理系统",-1),i={key:0},u={key:1};function l(t,o,e,l,n,d){const h=(0,a.up)("b-form-input"),c=(0,a.up)("b-form-group"),b=(0,a.up)("b-form-select"),m=(0,a.up)("b-form-tags"),k=(0,a.up)("b-button"),p=(0,a.up)("b-form"),g=(0,a.up)("b-container");return(0,a.wg)(),(0,a.iD)("div",null,[(0,a.Wm)(g,null,{default:(0,a.w5)((()=>[s,n.book.id?((0,a.wg)(),(0,a.iD)("h4",i,"编辑图书")):((0,a.wg)(),(0,a.iD)("h4",u,"新增图书")),(0,a.Wm)(p,{onSubmit:(0,r.iM)(d.onSubmit,["prevent"])},{default:(0,a.w5)((()=>[(0,a.Wm)(c,{label:"标题"},{default:(0,a.w5)((()=>[(0,a.Wm)(h,{modelValue:n.book.title,"onUpdate:modelValue":o[0]||(o[0]=t=>n.book.title=t),onKeydown:o[1]||(o[1]=(0,r.D2)((0,r.iM)((()=>{}),["prevent"]),["enter"]))},null,8,["modelValue"])])),_:1}),(0,a.Wm)(c,{label:"类型"},{default:(0,a.w5)((()=>[(0,a.Wm)(b,{modelValue:n.book.category.id,"onUpdate:modelValue":o[2]||(o[2]=t=>n.book.category.id=t),options:n.categories,"value-field":"id","text-field":"name"},null,8,["modelValue","options"])])),_:1}),(0,a.Wm)(c,{label:"作者"},{default:(0,a.w5)((()=>[(0,a.Wm)(m,{modelValue:n.authorTags,"onUpdate:modelValue":o[3]||(o[3]=t=>n.authorTags=t),separator:" ,;",placeholder:"输入作者用空格，逗号，分号分隔","remove-on-delete":"true","duplicate-tag-text":"重复作者",onKeydown:o[4]||(o[4]=(0,r.D2)((0,r.iM)((()=>{}),["prevent"]),["enter"]))},null,8,["modelValue"])])),_:1}),(0,a.Wm)(k,{type:"submit",variant:"primary"},{default:(0,a.w5)((()=>[(0,a.Uk)("保存")])),_:1}),(0,a.Uk)("   "),(0,a.Wm)(k,{variant:"secondary",onClick:o[5]||(o[5]=o=>t.$router.push("/"))},{default:(0,a.w5)((()=>[(0,a.Uk)("取消")])),_:1})])),_:1},8,["onSubmit"])])),_:1})])}var n=e(4161),d={name:"BookForm",data(){return{bookId:0,book:{title:"",category:{id:null},authors:[]},categories:[],authors:[],authorTags:[],selectedAuthorId:null}},methods:{tagValidator(t){},async getBook(){if(this.bookId)try{const t=await n.Z.get(`/api/books/${this.bookId}`);this.book=t.data,this.authorTags=this.book.authors.map((t=>t.name))}catch(t){console.error(t)}},async getCategories(){try{const t=await n.Z.get("/api/categories");this.categories=t.data}catch(t){console.error(t)}},async getAuthors(){try{const t=await n.Z.get("/api/authors");this.authors=t.data}catch(t){console.error(t)}},async onSubmit(){try{this.book.authorTags=this.authorTags,this.bookId?await n.Z.put(`/api/books/${this.bookId}`,this.book):await n.Z.post("/api/books",this.book)}catch(t){console.error(t)}await this.$router.push({name:"BookList"})}},async created(){this.bookId=this.$route.query.bookId||0,await Promise.all([this.getBook(),this.getCategories(),this.getAuthors()])}},h=e(89);const c=(0,h.Z)(d,[["render",l]]);var b=c}}]);
//# sourceMappingURL=965.b9c10537.js.map