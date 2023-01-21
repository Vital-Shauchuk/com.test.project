package models.projectModels;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetResponseResult {
    @Expose
    boolean status;
    @Expose
    public ProjectResult result;
}
