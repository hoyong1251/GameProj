package com.hotmail.kalebmarc.textfighter.main;

class Version {
    private static final String VERSION = "\t Edited by hoyong";
  //  private static final String STAGE = "Alpha";
    private static final String DESC = "" //Award for "worse game description" goes to:
            //But seriously; add information that the user actually cares about
            + "Text-Fighter ��� ���� �Դϴ�.\n"
            + "RPG������ �����̰� �ڹٷ� �ڵ��Ǿ����ϴ�.\n"
            + "\n\n"
            + "���� �� �ڵ带 �����ϰ� ������ ����� �̹����� ���带 �����Ͽ�\n"
            + "���� �� ����ó�� �����ְ� �ϰ� �;����ϴ�.\n"
            + "����鵵 �ѱ۷� ���� ���������Դϴ�";
    private static final String CHANGE_LOG = ""
            + "������ ����� ���鿡���� \n"
    		+"�� ����� ���Ͽ��� �������� �ð��� ����Ͽ�\n"
            +"�������� �ð���ŭ �߰� ������ �ִ� ����� �����غý��ϴ�.\n";

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
