package models.suitesModels;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuiteResponseModel {
    @Expose(serialize = false)
    int id;
    @Expose
    String title;
    @Expose
    String description;
    @Expose
    String preconditions;
}


