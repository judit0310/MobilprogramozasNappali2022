package hu.uni.miskolc.mobilprogramozasnappali2022.service;

public class DolgozoDTO {

    private int id;
    private String keresztNev;
    private String vezetekNev;
    private int fizetes;

    public DolgozoDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeresztNev() {
        return keresztNev;
    }

    public void setKeresztNev(String keresztNev) {
        this.keresztNev = keresztNev;
    }

    public String getVezetekNev() {
        return vezetekNev;
    }

    public void setVezetekNev(String vezetekNev) {
        this.vezetekNev = vezetekNev;
    }

    public int getFizetes() {
        return fizetes;
    }

    public void setFizetes(int fizetes) {
        this.fizetes = fizetes;
    }

    @Override
    public String toString() {
        return "DolgozoDTO{" +
                "id=" + id +
                ", keresztNev='" + keresztNev + '\'' +
                ", vezetekNev='" + vezetekNev + '\'' +
                ", fizetes=" + fizetes +
                '}';
    }
}
