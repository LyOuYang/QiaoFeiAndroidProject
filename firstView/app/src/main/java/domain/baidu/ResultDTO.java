package domain.baidu;

public class ResultDTO {
    private String score;
    private String name;
    private BaikeInfoDTO baike_info;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BaikeInfoDTO getBaike_info() {
        return baike_info;
    }

    public void setBaike_info(BaikeInfoDTO baike_info) {
        this.baike_info = baike_info;
    }
}
