package domain.baidu;

import java.util.List;

public class ReferDTO {
    private List<String> sources;
    private List<String> license;

    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    public List<String> getLicense() {
        return license;
    }

    public void setLicense(List<String> license) {
        this.license = license;
    }
}
