import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BooleanYNConverter implements AttributeConverter<Boolean, String> {

    public Boolean converted;


    @Override
    public String convertToDatabaseColumn(Boolean value) {
        if (value) {
            converted = true;
            return "yes";
        }
        else {
            return "no";
        }
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        if (s.equals("yes")) {
            return true;
        }
        else {
            return false;
        }
    }
}
