# spring-boot-demo
これは、ユーザーアカウント管理パッケージの Demo Projectです。  
書籍「ユースケース駆動開発実践ガイド」に記載の手法を模倣してクラス設計をし、Spring Boot フレームワークの提供するMVC環境上で動作するコードを示します。

---

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [spring-boot-demo](#spring-boot-demo)
- [レビューしてもらいたいもの](#%E3%83%AC%E3%83%93%E3%83%A5%E3%83%BC%E3%81%97%E3%81%A6%E3%82%82%E3%82%89%E3%81%84%E3%81%9F%E3%81%84%E3%82%82%E3%81%AE)
  - [レビュー対象の設計資料＆ソースコード](#%E3%83%AC%E3%83%93%E3%83%A5%E3%83%BC%E5%AF%BE%E8%B1%A1%E3%81%AE%E8%A8%AD%E8%A8%88%E8%B3%87%E6%96%99%EF%BC%86%E3%82%BD%E3%83%BC%E3%82%B9%E3%82%B3%E3%83%BC%E3%83%89)
  - [設計面のレビュー観点](#%E8%A8%AD%E8%A8%88%E9%9D%A2%E3%81%AE%E3%83%AC%E3%83%93%E3%83%A5%E3%83%BC%E8%A6%B3%E7%82%B9)
  - [実装面のレビュー観点](#%E5%AE%9F%E8%A3%85%E9%9D%A2%E3%81%AE%E3%83%AC%E3%83%93%E3%83%A5%E3%83%BC%E8%A6%B3%E7%82%B9)
  - [設計資料の概要](#%E8%A8%AD%E8%A8%88%E8%B3%87%E6%96%99%E3%81%AE%E6%A6%82%E8%A6%81)
  - [システムおよび機能の実行手順](#%E3%82%B7%E3%82%B9%E3%83%86%E3%83%A0%E3%81%8A%E3%82%88%E3%81%B3%E6%A9%9F%E8%83%BD%E3%81%AE%E5%AE%9F%E8%A1%8C%E6%89%8B%E9%A0%86)
- [設計資料の補足情報](#%E8%A8%AD%E8%A8%88%E8%B3%87%E6%96%99%E3%81%AE%E8%A3%9C%E8%B6%B3%E6%83%85%E5%A0%B1)
  - [設計における２つのテーマ](#%E8%A8%AD%E8%A8%88%E3%81%AB%E3%81%8A%E3%81%91%E3%82%8B%EF%BC%92%E3%81%A4%E3%81%AE%E3%83%86%E3%83%BC%E3%83%9E)
    - [■1. オブジェクト指向言語上のWeb系MVCフレームワークで共通して使えるアプリケーション設計資料を作る](#%E2%96%A01-%E3%82%AA%E3%83%96%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E6%8C%87%E5%90%91%E8%A8%80%E8%AA%9E%E4%B8%8A%E3%81%AEweb%E7%B3%BBmvc%E3%83%95%E3%83%AC%E3%83%BC%E3%83%A0%E3%83%AF%E3%83%BC%E3%82%AF%E3%81%A7%E5%85%B1%E9%80%9A%E3%81%97%E3%81%A6%E4%BD%BF%E3%81%88%E3%82%8B%E3%82%A2%E3%83%97%E3%83%AA%E3%82%B1%E3%83%BC%E3%82%B7%E3%83%A7%E3%83%B3%E8%A8%AD%E8%A8%88%E8%B3%87%E6%96%99%E3%82%92%E4%BD%9C%E3%82%8B)
    - [■2. ユースケース駆動＆テスト駆動な開発を、フレームワークに依存しないところで実践する](#%E2%96%A02-%E3%83%A6%E3%83%BC%E3%82%B9%E3%82%B1%E3%83%BC%E3%82%B9%E9%A7%86%E5%8B%95%EF%BC%86%E3%83%86%E3%82%B9%E3%83%88%E9%A7%86%E5%8B%95%E3%81%AA%E9%96%8B%E7%99%BA%E3%82%92%E3%83%95%E3%83%AC%E3%83%BC%E3%83%A0%E3%83%AF%E3%83%BC%E3%82%AF%E3%81%AB%E4%BE%9D%E5%AD%98%E3%81%97%E3%81%AA%E3%81%84%E3%81%A8%E3%81%93%E3%82%8D%E3%81%A7%E5%AE%9F%E8%B7%B5%E3%81%99%E3%82%8B)
  - [上記テーマがもたらすメリット](#%E4%B8%8A%E8%A8%98%E3%83%86%E3%83%BC%E3%83%9E%E3%81%8C%E3%82%82%E3%81%9F%E3%82%89%E3%81%99%E3%83%A1%E3%83%AA%E3%83%83%E3%83%88)
    - [フレームワーク選定が確定する前でもビジネスロジックの実装やテストを進められる](#%E3%83%95%E3%83%AC%E3%83%BC%E3%83%A0%E3%83%AF%E3%83%BC%E3%82%AF%E9%81%B8%E5%AE%9A%E3%81%8C%E7%A2%BA%E5%AE%9A%E3%81%99%E3%82%8B%E5%89%8D%E3%81%A7%E3%82%82%E3%83%93%E3%82%B8%E3%83%8D%E3%82%B9%E3%83%AD%E3%82%B8%E3%83%83%E3%82%AF%E3%81%AE%E5%AE%9F%E8%A3%85%E3%82%84%E3%83%86%E3%82%B9%E3%83%88%E3%82%92%E9%80%B2%E3%82%81%E3%82%89%E3%82%8C%E3%82%8B)
    - [移植性がかなり高まる](#%E7%A7%BB%E6%A4%8D%E6%80%A7%E3%81%8C%E3%81%8B%E3%81%AA%E3%82%8A%E9%AB%98%E3%81%BE%E3%82%8B)
- [実装に関する補足情報](#%E5%AE%9F%E8%A3%85%E3%81%AB%E9%96%A2%E3%81%99%E3%82%8B%E8%A3%9C%E8%B6%B3%E6%83%85%E5%A0%B1)
  - [Springセキュリティの利用について](#spring%E3%82%BB%E3%82%AD%E3%83%A5%E3%83%AA%E3%83%86%E3%82%A3%E3%81%AE%E5%88%A9%E7%94%A8%E3%81%AB%E3%81%A4%E3%81%84%E3%81%A6)
  - [LoadDatabaseクラスについて](#loaddatabase%E3%82%AF%E3%83%A9%E3%82%B9%E3%81%AB%E3%81%A4%E3%81%84%E3%81%A6)
  - [レビュー対象ユースケースにおける現状のユースケース記述に対して未実装の事項](#%E3%83%AC%E3%83%93%E3%83%A5%E3%83%BC%E5%AF%BE%E8%B1%A1%E3%83%A6%E3%83%BC%E3%82%B9%E3%82%B1%E3%83%BC%E3%82%B9%E3%81%AB%E3%81%8A%E3%81%91%E3%82%8B%E7%8F%BE%E7%8A%B6%E3%81%AE%E3%83%A6%E3%83%BC%E3%82%B9%E3%82%B1%E3%83%BC%E3%82%B9%E8%A8%98%E8%BF%B0%E3%81%AB%E5%AF%BE%E3%81%97%E3%81%A6%E6%9C%AA%E5%AE%9F%E8%A3%85%E3%81%AE%E4%BA%8B%E9%A0%85)
  - [のちに、上記ユースケースに追加しようとしている事項](#%E3%81%AE%E3%81%A1%E3%81%AB%E4%B8%8A%E8%A8%98%E3%83%A6%E3%83%BC%E3%82%B9%E3%82%B1%E3%83%BC%E3%82%B9%E3%81%AB%E8%BF%BD%E5%8A%A0%E3%81%97%E3%82%88%E3%81%86%E3%81%A8%E3%81%97%E3%81%A6%E3%81%84%E3%82%8B%E4%BA%8B%E9%A0%85)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

---

# レビューしてもらいたいもの

- **Spring Boot環境上で実装したユーザアカウント管理機能** の一部のユースケース

## レビュー対象の設計資料＆ソースコード

- Eclipseから「新規＞Spring スターター・プロジェクト」で生成されるディレクトリ構成に準じて、「ユーザアカウント管理機能」の実装を進めています。
- docs/配下に設計資料があります。
    - Spring Boot 上で実装した「ユーザアカウント管理機能」のうちの次のユースケース２点の実装内容をレビューしてもらいたいです。 
        1. **ユースケース「アカウント作成の申請をする」**
        2. **ユースケース「システムへログインする」**

`上記２点の設計はひとまず完了していますが、実装は未完了部分があります。`  
`実装が完了していない部分は、「社内講習の開発演習の課題にする」などへの活用を考えています。`


## 設計面のレビュー観点

- [ ] クラス設計にアンチパターンは見られるか？
- [ ] クラス設計について十分な説明が必要な箇所はどこか？
- [ ] その他さまざま指摘あればください...

## 実装面のレビュー観点

- [ ] 「TableModel」と名付けたパッケージの役割について
    - [ ] これらはDAO(Data Access Object)およびDTO(Data Transfer Object)なわけだが、妥当な使い方か？
- [ ] 「AggregateRepository」と名付けたパッケージの役割について
    - [ ] 現状の実装が目指していることを理解してもらえるか？
        - ポイントは [ masuda220 さんのこの記事](http://masuda220.jugem.jp/?eid=317) にある「 **Repository は、Aggregate に対で作成する。** 」という文言。
- [ ] テンプレートエンジンの使い方について
    - [ ] 見直した方が良いところはあるか？
- [ ] その他さまざま指摘あればください...


## 設計資料の概要

1. [docs/MngUserAccount/Required.md](/docs/MngUserAccount/Required.md) で、対象システムにおける一般ユーザアカウント管理機能に要求されている振る舞いをリストしています。(いわゆる「要求定義」を、ICONIXプロセスに反映させるためのリストとして分解したもの)
2. [docs/MngUserAccount/一般ユーザアカウント管理機能_概念モデル図.png](docs/MngUserAccount/一般ユーザアカウント管理機能_概念モデル図.png) で、対象の問題領域(ドメイン)をモデリングしています。
3. [docs/MngUserAccount/補助資料.1_一般ユーザアカウントのライフサイクル.png](docs/MngUserAccount/補助資料.1_一般ユーザアカウントのライフサイクル.png) で、このアカウント管理機能が組み込まれるシステム内での「一般ユーザ」のライフサイクルを示しています。
4. [docs/MngUserAccount/SytemAdminOperation/システム管理操作UseCase図.png](docs/MngUserAccount/SytemAdminOperation/システム管理操作UseCase図.png) で、このアカウント管理機能におけるシステム管理者向けのユースケースを図示しています。(同ディレクトリ内にそれらのユースケース記述やGUIモックも併置していますが、これらの設計は現時点で未着手です。)
5. [docs/MngUserAccount/UserOperation/一般ユーザ操作UseCase図.png](docs/MngUserAccount/UserOperation/一般ユーザ操作UseCase図.png) で、このアカウント管理機能における一般ユーザ向けのユースケースを図示しています。
6. [docs/MngUserAccount/UserOperation/アカウント作成の申請をする/](docs/MngUserAccount/UserOperation/アカウント作成の申請をする/) ディレクトリには、ユースケース「アカウント作成の申請をする」に関する次の設計資料が収められています。
    1. ユースケース記述
    2. ロバストネス図
    3. GUIモック
    4. シーケンス図
    5. クラス図
7. [docs/MngUserAccount/UserOperation/システムへログインする/](docs/MngUserAccount/UserOperation/システムへログインする/) ディレクトリには、ユースケース「システムへログインする」に関する次の設計資料が収められています。
    1. ユースケース記述
    2. ロバストネス図
    3. GUIモック
    4. シーケンス図
    5. クラス図


## システムおよび機能の実行手順

1. git でローカルPC環境へ clone する。
2. 前項のローカルgitリポジトリで demo ブランチを checkout する。
3. 最新の Eclipse(Full Edition)環境 もしくは Spring Tool Suite環境 へ、 前項で checkout したプロジェクトをインポートする。
4. src/test/java に対して、JUnitテストを実行する。
5. src/main/java/page/clapandwhistle/uam/SampleUserAccountManagementApplication.java を Spring Boot アプリケーションとして起動する。
6. 以下の各種操作を行う（**URLはブラウザのアドレスバーへ直接入力してください**）
    1. http://localhost:8081/
        - Home の表示がされる
    2. http://localhost:8081/user-account/login
        - ログイン画面が表示される
        - Email「hoge01@example.local」、password「hoge01TEST」でログインを行い、「ログイン完了」の文字のある画面が表示される
    3. http://localhost:8081/user-account/login
        - 前項とは異なる任意のメールアドレスでログインを行い、コンソールに「入力されたメールアドレスを使用するユーザはいません」のエラーメッセージが表示される
    4. http://localhost:8081/user-account/new
        - アカウント作成の申請画面が表示される
        - Email「hoge02@example.local」、password「hoge02TEST」で「新規アカウントを作成する」ボタンのクリックを行い、Home の表示がされる
    5. http://localhost:8081/user-account/login
        - Email「hoge02@example.local」、password「hoge02TEST」でログインを行い、「ログイン完了」の文字のある画面が表示される
    - ※実は上記の 2. と 5. の結果は要件定義・設計の考慮漏れ。
        - 「申請中」のステータスであるにも関わらずログインできてしまっているのは間違った挙動です。
        - 後ほど修正します。(要件を「申請中でもログインはでき、別の制限がある」と変えて修正を回避するのもありか?)

---

以上が、レビューしてほしいことの概要です。

**基本はレビューMeeting時に私から口頭で説明を行うことを前提にしています**が、参考までに以下へ設計や実装にまつわる考え方などの補足情報を示しておきます。

---


# 設計資料の補足情報

## 設計における２つのテーマ

※「テーマ」は「方針」と言い換えても良い。

### ■1. オブジェクト指向言語上のWeb系MVCフレームワークで共通して使えるアプリケーション設計資料を作る

書籍「 [ユースケース駆動開発実践ガイド ](https://www.shoeisha.co.jp/book/detail/9784798114453) 」で紹介されている **ICONIXプロセス** に則って分析・設計を行っています。  
ユースケース記述ごとにロバストネス分析を行い、そこからシーケンス図を描きつつ並行してクラス図を生成し、そのクラス図が示した構成を、言語やフレームワークをまたいで共通に使います。

### ■2. ユースケース駆動＆テスト駆動な開発を、フレームワークに依存しないところで実践する

DDDで言うところの [オニオンアーキテクチャ](https://qiita.com/little_hand_s/items/ebb4284afeea0e8cc752#%E3%82%AA%E3%83%8B%E3%82%AA%E3%83%B3%E3%82%A2%E3%83%BC%E3%82%AD%E3%83%86%E3%82%AF%E3%83%81%E3%83%A3) が示す考え方をいくつか参考にして取り入れています。  
それにより、フレームワーク(UI層やInfra層やTest層)が何と差し替わろうともビジネスロジックが影響されない実装を行えます。

## 上記テーマがもたらすメリット

### フレームワーク選定が確定する前でもビジネスロジックの実装やテストを進められる

- ユースケースで表現されるビジネスロジックは、Java や PHP や Python といった各言語のピュアな言語機能のみによってテスト駆動で実装します。
- そのため、プロダクトに使用するフレームワークやサードパーティ製ライブラリの選定期限に猶予がもたらされます。
- このことは、特に社内の人材育成プログラムにおいては次のようなメリットを生むでしょう。
    - フレームワークの実行環境がなんらかの事情で動かない場合でも、実装の話ができる。
    - 使用するフレームワークを(比較等のため)実験的に差し替えることの難易度(ハードル)が下がる。
    - 受講者が学習を希望する言語やフレームワークに応じて講師が模範解答などを用意する際に、講師が対象フレームワークに不慣れでも把握を焦らずに済む。

### 移植性がかなり高まる

- **ビジネスロジックを** ユースケース層以降へ凝集するため、言語が同じならそれらは **まさにそのまま** 、また異なる言語でもクラス構成や属するメソッドの設計上のシグネチャ等はそのまま、 **移植できます** 。
    - なお、システム全体の移植に際して書き直しが必要になるのは次の事項です。
        - テストの記述
        - データソースから集約オブジェクトへのマッピング
        - プレゼンテーション層への値のマッピング
    - とは言えこれらも、しっかりビジネスロジックをユースケース層以降へ凝集できていれば、それぞれのコーディングはとてもシンプルな作業になります。
- これを証明するべく、レビュー後の次のステップとして **「現状のJava向け実装と同じクラス構成でPHP向けの実装が実現できる」** ことを示す予定。



# 実装に関する補足情報

## Springセキュリティの利用について

- WebSecurityConfigurerAdapter(の実装クラス) で、ひとまず開発作業用にセキュリティを緩くしてあります。
- セキュリティ要件を細かく揉んでないのでどんな設定にするべきか決まっていないが、要件を決めたところで実装(設定)の仕方については今の所まったく把握していません。

## LoadDatabaseクラスについて

- 開発作業用に初期データを登録する為のコードを書いてあります。
- つまりDebug用の実装なので、仮にこの実装をなにかのプロダクトへ組み込んでリリースするときには除去するべきでしょう。

## レビュー対象ユースケースにおける現状のユースケース記述に対して未実装の事項

-  *ユースケース「アカウント作成の申請をする」*
    - `プレゼンテーション層`: **「氏名」「生年月日」の入力欄**
    - `プレゼンテーション層`: **エラーメッセージの表示**
    - `インフラ層`: **パスワードのハッシュ化**
-  *ユースケース「システムにログインする」*
    - `プレゼンテーション層`: **セッションの開始**
    - `プレゼンテーション層`: **認証が必要なページのセッション制御**

※上記で「プレゼンテーション層」の記述が指すのはMVCフレームワークの V(View) と C(Controller) のことです。
※上記で「インフラ層」の記述が指すのは、当ソースコードにおいては src/main/java/page/clapandwhistle/uam/infrastructure/配下へ行われる各種実装のことです。

## のちに、上記ユースケースに追加しようとしている事項

- アカウント作成申請時のメール送信
