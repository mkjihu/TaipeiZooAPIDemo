# 台北市立動物園 API Demo




**基本架構**
---
1.使用框架、library：
 okhttp、retrofit、rxjava3、eventbus、eventbus、glide

2.程式架構
基本應該算是個人化的MVP架構(但我有用上lifecycle.ViewModel)
 #### 架構分類：
 - Interactor + ViewModel 負責API呼叫 處裡
 - Presenter負責一些邏輯處理(由ViewModel去觀察資料)
 - View(**Page)則是頁面，負責顯示資料

---
