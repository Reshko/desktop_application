package sample.zakaz.basketTovar;

public class ZakazTable {
    private int idTable;
    private String nameTable;
    private int numberTable;

    public ZakazTable(int idTable, String nameTable, int numberTable) {
        this.idTable = idTable;
        this.nameTable = nameTable;
        this.numberTable = numberTable;
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public int getNumberTable() {
        return numberTable;
    }

    public void setNumberTable(int numberTable) {
        this.numberTable = numberTable;
    }
}
