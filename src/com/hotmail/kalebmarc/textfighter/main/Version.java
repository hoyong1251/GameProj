package com.hotmail.kalebmarc.textfighter.main;

class Version {
    private static final String VERSION = "\t Edited by hoyong";
  //  private static final String STAGE = "Alpha";
    private static final String DESC = "" //Award for "worse game description" goes to:
            //But seriously; add information that the user actually cares about
            + "Text-Fighter 라는 게임 입니다.\n"
            + "RPG형식의 게임이고 자바로 코딩되었습니다.\n"
            + "\n\n"
            + "제가 이 코드를 수정하고 스윙을 사용해 이미지와 사운드를 삽입하여\n"
            + "조금 더 게임처럼 보여주게 하고 싶었습니다.\n"
            + "영어들도 한글로 전부 번역예정입니다";
    private static final String CHANGE_LOG = ""
            + "게임의 기능적 측면에서는 \n"
    		+"각 저장된 파일에서 오프라인 시간을 계산하여\n"
            +"오프라인 시간만큼 추가 보상을 주는 방식을 생각해봤습니다.\n";

    private Version() {
    }

    public static String get() {
        return VERSION;
    }

   // public static String getStage() {
  //      return STAGE;
   // }

    public static String getFull() {
        return " " + VERSION;
    }

    public static String getDesc() {
        return DESC;
    }

    public static String getChange() {
        return CHANGE_LOG;
    }
}
