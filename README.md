# sevens

七並べのAIを戦わせるProcessing用ライブラリ

## 使い方

downloadして→ [https://github.com/mayoneko/sevens/releases](https://github.com/mayoneko/sevens/releases)

Processingのlibraryフォルダに入れて

exampleを見て→ [https://github.com/mayoneko/sevens/tree/master/example](https://github.com/mayoneko/sevens/tree/master/example)

オリジナルのchoiceCard関数を実行！

## ルール

1. プレイヤーは3人
1. ジョーカーは使わない
1. ダイヤの7を出したプレイヤーから始める
1. AからK、KからAにはつながらない
1. 自分のターンでは7からつながるカードを1枚出すかパスする
1. 3回までパスできるが、4回目のパスをしようとしたら負けになり、残った手札を場にすべて置く
1. 負けにより配置されたカードがあっても、出すことのできるカードは7から一番近い空き札部分だけ

## choiceCard関数について

### 引数

board
- List<Card>
- 場にあるカードのリスト
- Cardに入っているもの
  - id
    - int
  - suit
    - int
    - https://github.com/mayoneko/sevens/blob/master/src/main/kotlin/sevens/Card.kt#L6
  - number
    - int
  
hand
- List<Card>
- 手札のカードのリスト
  
remainingPassCount
- int
- 自分があと何回パスできるか

otherPlayersStatus
- List<Player.Status>
- 他のプレイヤーのデータ
- Player.Statusに入っているもの
  - id
    - int
  - state
    - int
    - https://github.com/mayoneko/sevens/blob/master/src/main/kotlin/sevens/Player.kt#L5
  - handNum
    - int
    - 手札の枚数
  - lastChosenCard
    - Card?
    - 最後に選んだカード
    - nullならパス
  - remainingPassCount
    - int
   
