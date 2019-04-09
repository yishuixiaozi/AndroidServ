package com.hhit.model;

public enum JobtypeBean{
    JIA_JIAO("家教老师","jiajiao"),
    WAI_MAI("餐饮服务","waimai"),
    CHUAN_DAN("发布传单","chuandan");

    String chineseName;
    String englishName;

    //构造方法
    JobtypeBean( String chineseName, String englishName) {
        this.chineseName = chineseName;
        this.englishName = englishName;
    }

    //普通方法,依据外来传递进来的中文返回他对应的英文
    public static String getTypeEnglish(String TypeChinese){
        for (JobtypeBean jobtypeBean : JobtypeBean.values()){
            if(jobtypeBean.getChineseName() == TypeChinese){
                return jobtypeBean.getEnglishName();
            }
        }
        return null;
    }


    //Set 和 Get方法
    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }
}
