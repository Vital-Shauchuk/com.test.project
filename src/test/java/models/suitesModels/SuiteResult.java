package models.suitesModels;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuiteResult {
    @Expose
    int id;

}
