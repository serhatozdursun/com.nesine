Feature: Kupondas


  Scenario Outline: Correct transfer of coupons on the all shares page to the basket
    Given Nesine app runing on "<platform>"
    When I click "<mainPagesingin>" button.
    And I fill to field  user name and password, i click "<loginPageSignIn>" button.
    And I Click "<kupondas>" button on the homepage.
    And I click on the "<allShares>" tab on the page that opens, the first "<playable>" button displayed is selected.
    And I got the event names on the "<couponDetail>" page, I click the "<playNow>" button.
    Then I check that the event names are correct, on the basket page
    Examples:
      | platform | mainPagesingin | loginPageSignIn | kupondas | allShares       | playable   | playNow    | couponDetail |
      | Android  | GİRİŞ YAP      | Giriş Yap       | Kupondaş | Tüm Paylaşımlar | Hemen Oyna | Hemen Oyna | KUPON DETAYI |
