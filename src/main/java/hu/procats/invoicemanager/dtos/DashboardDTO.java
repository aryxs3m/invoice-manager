package hu.procats.invoicemanager.dtos;

public class DashboardDTO {
    private String search;
    private String column;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }
}
