package domain.baidu;

import java.util.List;

public class LocalDomain {
    private String code;
    private List<LocationDTO> location;
    private ReferDTO refer;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<LocationDTO> getLocation() {
        return location;
    }

    public void setLocation(List<LocationDTO> location) {
        this.location = location;
    }

    public ReferDTO getRefer() {
        return refer;
    }

    public void setRefer(ReferDTO refer) {
        this.refer = refer;
    }
}
