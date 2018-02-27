import java.io.BufferedReader;
import java.io.FileReader;

public class Process {

    String CADENA_VACIA = "";

    public void testCommit(){
        System.out.println("Una prueba de commit");
        for (int i = 0; i<10; i++){
            System.out.println("Prueba de for");
            i = i+1;
        }
    }



    public void codes(){

        BufferedReader br = null;
        FileReader fr = null;
        String line = CADENA_VACIA;
        try{
            fr = new FileReader("C:\\temporal\\total_codigos.txt");
            br = new BufferedReader(fr);

            while ( (line = br.readLine()) != null ){
                String codigo = convertUpcAToUpcE(line.substring(1));
                System.out.println(codigo+"--");
            }

        }catch (Exception e){
            System.out.println(e.getCause());
        }
    }


    public void test(String code){

        String code_to_test= code ;
        String test_code = convertUpcAToUpcE(code_to_test);
        if(test_code.equals(code_to_test)){
            System.out.println("xxxxxx");
        }else{
            System.out.println("-->"+test_code);
        }
    }

    //  Con esta función vamos a convertir en caso de que aplique, un código UPC-A a UPC-E
    public String convertUpcAToUpcE(String inCodeUpcA){
        String outCode = CADENA_VACIA;
        // Revisamos el código y su longitud
        System.out.println(inCodeUpcA + " - " + inCodeUpcA.length());
        // Será tratado el código solamente si es de longitud 12
        if(inCodeUpcA.length()==12){
            // extraemos los códigos de las posiciones estándar de los formatos de códigos de barras tipo upc-a y upc-e
            String startDigit = inCodeUpcA.substring(0,1);
            String checkDigit = inCodeUpcA.substring(11,12);
            String manufacturerCode = inCodeUpcA.substring(1,6);
            String productCode = inCodeUpcA.substring(6,11);
            // Imprimimos los códigos INICIO, DIGITO VERIFICADOR, FABRICANTE Y CÓDIGO DEL PRODUCTO
            System.out.println("S " + startDigit + " - C " + checkDigit + " - M " + manufacturerCode + " - P " + productCode);
            // Iniciamos el proceso de conversión si aplica
            if("0".equals(startDigit) || "1".equals(startDigit)){
                if((manufacturerCode.substring(2,5).equals("000") || manufacturerCode.substring(2,5).equals("100") || manufacturerCode.substring(2,5).equals("200")) && Integer.parseInt(productCode)<=999){
                    System.out.println("Cas A - " + manufacturerCode.substring(2,5));
                    outCode = startDigit + manufacturerCode.substring(0,2) + productCode.substring(2,5) + productCode.substring(2,3) + checkDigit;
                }else if(manufacturerCode.substring(3,5).equals("00") && Integer.parseInt(productCode)<=99){
                    System.out.println("Cas B");
                    outCode = startDigit + manufacturerCode.substring(0,3) + productCode.substring(3,5) + "3" + checkDigit;
                }else if(manufacturerCode.substring(4,5).equals("0") && Integer.parseInt(productCode)<=9){
                    System.out.println("Cas C");
                    outCode = startDigit + manufacturerCode.substring(0,4) + productCode.substring(4,5) + "4" + checkDigit;
                }else if(! manufacturerCode.substring(4,5).equals("0") && Integer.parseInt(productCode)>=5 && Integer.parseInt(productCode) <= 9){
                    System.out.println("Cas D");
                    outCode = startDigit + manufacturerCode + productCode.substring(4,5) + checkDigit;
                }else{
                    outCode = inCodeUpcA;
                }
            }else{
                outCode = inCodeUpcA;
            }
        }else{
            outCode = inCodeUpcA;
        }
        return outCode;
    }

}
