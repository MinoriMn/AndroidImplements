# AndroidImplements

## android-xについて
AndroidXからsupport libraryパッケージがandroidxパッケージに総移行する。それにあたってsupportの方は28.0.0で終了するので、以後はandroidxに変更する必要がある。

## 既存プロジェクトをandroidxに変更するには
1. `Refactor > Migrate to AndroidX`を選択
2. バックアップデータを保存するフォルダを選択
3. `Do Refactor`で出来る。

## メモ

- androidxのクラスはsupportのクラスとして利用できるっぽい(完全互換かは不明。AndroidStudioのエディタではエラーになるがビルドはできる。)
- androidxとsupportは共存できず、ビルドが失敗する。(既にandroidxを必要とするlibraryを利用するときはandroidxにするしかないか？)
