package shop.com.shopdb.ErrMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
public class ErrText {
    String vi;
    String en;

    public String getText(String lng){
        if(lng.equals("vi")){
            return vi;
        }else{
            return en;
        }
    }
}
