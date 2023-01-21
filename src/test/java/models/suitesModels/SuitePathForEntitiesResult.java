package models.suitesModels;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SuitePathForEntitiesResult {
    @Expose
    int total;
    @Expose
    int filtered;
    @Expose
    int count;
    @Expose
    List<SuiteResponseModel> entities;

}
