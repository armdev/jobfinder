package jobs.find.com.finddevjobs.models;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author home
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobModel {

    @JsonIgnore
    private Long id;
    private String jobId;
    private String created_at;
    private String title;
    private String location;
    private String type;
    private String description;
    private String how_to_apply;
    private String company;
    private String company_url;
    private String company_logo;
    private String url;
    @JsonIgnore
    private String dateInserted;
    @JsonIgnore
    private String searchedKey;


    public JobModel() {
    }


    public JobModel(Long id) {
        this.id = id;
    }

    public JobModel(Long id, String jobId, String created_at, String title, String location,
                    String type, String description, String how_to_apply, String
                            company, String company_url, String company_logo,
                    String url,
                    String dateInserted,
                    String searchedKey) {
        this.id = id;
        this.jobId = jobId;
        this.created_at = created_at;
        this.title = title;
        this.location = location;
        this.type = type;
        this.description = description;
        this.how_to_apply = how_to_apply;
        this.company = company;
        this.company_url = company_url;
        this.company_logo = company_logo;
        this.url = url;
        this.dateInserted = dateInserted;
        this.searchedKey = searchedKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }


    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHow_to_apply() {
        return how_to_apply;
    }

    public void setHow_to_apply(String how_to_apply) {
        this.how_to_apply = how_to_apply;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany_url() {
        return company_url;
    }

    public void setCompany_url(String company_url) {
        this.company_url = company_url;
    }

    public String getCompany_logo() {
        return company_logo;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDateInserted() {
        return dateInserted;
    }

    public void setDateInserted(String dateInserted) {
        this.dateInserted = dateInserted;
    }

    public String getSearchedKey() {
        return searchedKey;
    }

    public void setSearchedKey(String searchedKey) {
        this.searchedKey = searchedKey;
    }

    @Override
    public String toString() {
        return "Title: " + title + " Location " + location;
    }
}
