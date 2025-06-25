package com.example.demo.controller;
// BigDecimal型を扱う
import java.math.BigDecimal;
//Java の リスト型（順序付きのコレクション） を使う
import java.util.List;
/* Spring Boot のWeb機能を使うためのインポート
（ルーティング、画面表示、テンプレート連携など*/
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
//自作のデータアクセスとデータクラスを使う
import com.example.demo.dao.ShopDao;
import com.example.demo.data.ShopData;
import com.example.demo.form.ShopEditForm;
//入力登録フォーム
import com.example.demo.form.ShopRegistForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

//Web画面を返すコントローラー、自動設定有効
@Controller
@EnableAutoConfiguration
public class ShopController {

	// DBアクセスオブジェクトの生成（おまじない）
	// データアクセスオブジェクト（DAO）で、ユーザー情報から取得・保存する役割
	private final ShopDao ShopDao;
	// Spring が UserDao を自動で渡してくれる（コンストラクタインジェクション）
	public ShopController(ShopDao shopDao) {
		this.ShopDao = shopDao;
	}

	// ドラッグストア商品一覧
	@GetMapping({ "/products" }) //アクセスあれば呼び出す
	public ModelAndView shop(Model model, String keyword){
		// DBからデータ全件取得
		 List<ShopData> items = ShopDao.findAll();
		 
		 /*「くすり」がつくものを探す機能
		List<ShopData> items = userDao.findProducts("くすり");*/
		 
		// 検索する場合
		if (keyword != null && !keyword.isEmpty()) {
			items = ShopDao.findProducts(keyword);//検索
		} else {
			items = ShopDao.findAll();
		}
		// 粗利益を計算する
		for (int i=0; i < items.size(); i++) {//size=Listの要素数を取得する
			ShopData item = items.get(i);
			if(item.getSale_price() != null && item.getCost_price() != null) {//データが入っている場合
				BigDecimal grossProfit = item.getSale_price().subtract(item.getCost_price());//subtact=減算機能
				item.setGrossProfit(grossProfit);
			}
		}
		// templates/htmlにリンクされる
		// どの画面（View）を表示するか、どんなデータを渡すか、をまとめて返すクラス
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("products");

		// HTML側へデータをリンク
		modelAndView.addObject("items", items);//全検索した一覧を返す
		modelAndView.addObject("keyword", keyword);//何を検索したかのワードを返す
		return modelAndView;
	}

	// 新規登録画面の取得
	@GetMapping({ "/shop/registForm" })
	public ModelAndView registForm(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("shopRegistForm");
		return modelAndView;
	}

	// 新規登録
	@PostMapping({ "/shop/regist" })
	public ModelAndView regist(@ModelAttribute ShopRegistForm form, Model model) {
		// @ModelAttributeを使うと、フォームの名前とクラスのメンバ変数名を自動的にマッピングしてくれる

		// データ登録（本当は入力チェックとかが必要だが、今はやらない）
		ShopDao.insert(form);

		ModelAndView modelAndView = new ModelAndView();
		// 登録が成功したので一覧画面にリダイレクトする
		modelAndView.setViewName("redirect:/products");
		return modelAndView;
	}

	// 編集画面の取得
	@GetMapping({ "/shop/editForm" })
	public ModelAndView editForm(@RequestParam String barcode) {
		ShopData item = ShopDao.findBarcode(barcode); //barcodeで商品情報を取得
		//マッピング時、フィールド名の差を吸収、shopEditFormに詰め替える
		ShopEditForm form =new ShopEditForm();
		form.setBarcode(item.getBarcode());
		form.setName(item.getName());
		form.setCostPrice(item.getCost_price());
		form.setSalePrice(item.getSale_price());
		
		ModelAndView modelAndView = new ModelAndView("shopEditForm"); //編集リンクに渡し、商品情報を渡す
		modelAndView.addObject("shopEditForm", form);
		return modelAndView;
	}

	// 更新登録
	@PostMapping({"/shop/update" })
	public ModelAndView update(@ModelAttribute ShopEditForm form, Model model) {
		// @ModelAttributeを使うと、フォームの名前とクラスのメンバ変数名を自動的にマッピングしてくれる

		// データ登録（本当は入力チェックとかが必要だが、今はやらない）
		ShopDao.update(form);

		ModelAndView modelAndView = new ModelAndView();
		// 登録が成功したので一覧画面にリダイレクトする
		modelAndView.setViewName("redirect:/products");
		return modelAndView;
	}
}
