package models.suitesModels;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuiteIdResponseResult {
    @Expose
    boolean status;
    @Expose
    public SuiteResult result;

}
