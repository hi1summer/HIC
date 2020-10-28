package tsinghua.hic.pojo.dto;

public enum InfoTypeEnum {
    Undefined("无", 0), Producer("生产商", 1), Express("快递商", 2),
    Transaction("交易商", 3), VerifyUser("验证用户", 4);

    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    private int typeIndex;

    public int getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(int typeIndex) {
        this.typeIndex = typeIndex;
    }

    private InfoTypeEnum(String typeName, int typeIndex) {
        this.typeName = typeName;
        this.typeIndex = typeIndex;
    }

}
