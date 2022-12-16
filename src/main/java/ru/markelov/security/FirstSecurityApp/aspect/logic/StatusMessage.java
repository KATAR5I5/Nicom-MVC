package ru.markelov.security.FirstSecurityApp.aspect.logic;

public enum StatusMessage {
    SENT("Отправлено"), NOT_SENT("Не Отправлено"), ERROR("Ошибка"),     RESEND("Напомнить");
    String val;
    StatusMessage(String val){
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    @Override
    public String toString() {
        return val;
    }
}
