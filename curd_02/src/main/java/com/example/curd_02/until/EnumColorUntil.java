package com.example.curd_02.until;

/* 枚举类型匿名内部类*/
public class EnumColorUntil {
    public enum Color{
        //后面就是参数类型
        RED("red","红色"),BLUE("blue","蓝色"),BLACK("black","黑色");
        String yanSe;
        String chinese;

        Color(String yanSe,String chinese) {
            this.yanSe = yanSe;
            this.chinese = chinese;
        }

        String yanSe(){
            return yanSe;
        }
        String Chinese(){
            return chinese;
        }
    }

    public EnumColorUntil() {
    }

    public static String toYanSe(Color color){
        return color.yanSe;
    }
}
