package com.smk.cashier.model;

public class Stok extends Model {
    private int id;

    private String kodebarang;

    private int stokbarang;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKodebarang() {
        return kodebarang;
    }

    public void setKodebarang(String kodebarang) {
        this.kodebarang = kodebarang;
    }

    public int getStokbarang() {
        return stokbarang;
    }

    public void setStokbarang(int stokbarang) {
        this.stokbarang = stokbarang;
    }

    @Override
    public String toString() {
        return "Stok{" +
                "id=" + id +
                ", kodebarang='" + kodebarang + '\'' +
                ", stokbarang=" + stokbarang +
                ", dateCreated=" + dateCreated +
                ", lastModified=" + lastModified +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
