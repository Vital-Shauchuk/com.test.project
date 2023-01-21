package models.projectModels;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResponseResult {

    @Expose
    boolean status;
    @Expose
    public Result result;
}
