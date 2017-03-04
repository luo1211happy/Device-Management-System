package model;

public class Location {
    private String address;
    private String keeper_sn;
    private int sn;

    public Location() {
    }

    public Location(int sn, String keeper_sn, String address) {
        super();
        this.sn = sn;
        this.keeper_sn = keeper_sn;
        this.address = address;
    }

    public Location(String keeper_sn, String address) {
        this.setAddress(address);
        this.setKeeper_sn(keeper_sn);
    }

    public String getAddress() {
        return address;
    }

    public String getKeeper_sn() {
        return keeper_sn;
    }

    public int getSn() {
        return sn;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setKeeper_sn(String keeper_sn) {
        this.keeper_sn = keeper_sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }
}
