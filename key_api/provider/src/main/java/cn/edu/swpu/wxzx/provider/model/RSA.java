package cn.edu.swpu.wxzx.provider.model;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RSA {

        private  static  BigInteger E;
        private BigInteger[][] keys;
        private BigInteger[] public_Key;
        private BigInteger[] private_Key;

        public RSA(){
            E=new BigInteger("65537");
            keys=getKey();
            public_Key=keys[0];
            private_Key=keys[1];
        }

    public static BigInteger getE() {
        return E;
    }

    public static void setE(BigInteger e) {
        E = e;
    }

    public static BigInteger[][] getKey(){
            Random random=new Random();
            BigInteger p = BigInteger.probablePrime(1024, random);//第一个素数
            BigInteger q = BigInteger.probablePrime(1024, random);//第二个素数
            BigInteger n = p.multiply(q) ;
            BigInteger zn = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)) ;
            BigInteger d= E.modInverse(zn);
            return new BigInteger[][]{{n , E}, {n , d}} ;
        }


        public String getPublicKeyAndN(){
            return public_Key[0].toString();
        }


        public Map<String,String> getPrivateKeyAndN(){
            Map<String,String> map=new HashMap<String, String>();
            map.put("d",private_Key[1].toString());
            map.put("n",private_Key[0].toString());
            return map;
        }

        public String encrypt(String data,String n){
            return  (new BigInteger(data).modPow(E, new BigInteger(n))).toString();
        }
        public String decrypt(String data,String private_key,String n){
            return  (new BigInteger(data).modPow(new BigInteger(private_key),new BigInteger(n))).toString();
        }

        public static void main(String[] args) {
            RSA rsa=new RSA();
            String x=rsa.encrypt("10","24647339254027656223469414323596112924673349438150448448726863885067582658612094533781591231383216126201142454130787321730371026945032505155058750629450658634994930615308022903498852910565834233569248698471650862007452051156942981521394752315733935543429607628176264098143999148818291921806171068554327638406309589827750500338831986461286211174566188640562338073326685584628277246308115533597456747709550411715558518832471904164421694556105117325926283101462188671410832890817022438821825740816250113973451766541491834874157046643307571699195243678706823471511852169556589447274497854752587596599458099212139043546263");
            System.out.println("密文="+x);
            System.out.println(rsa.decrypt(x,"10786431132715370067981846471688666170141390897288447929534998292378707268133321380645891910024902295124509311181853169576708903730876867728950669496670191499615325769834588169968881705417377842333332650577008687813536357770912334299939315061225948781926309968166431643176648909581723768395300248977015594200563615762875556037018291242631808689530337701173082564188973145421073383374506946072992491621683213212973098442146648183929194346423996695150061236669974501437520512827029938606064027833436974518275445625484209931973814889755001169161571731830430272317041857767394810434637952383358755961562886057410964287297","24647339254027656223469414323596112924673349438150448448726863885067582658612094533781591231383216126201142454130787321730371026945032505155058750629450658634994930615308022903498852910565834233569248698471650862007452051156942981521394752315733935543429607628176264098143999148818291921806171068554327638406309589827750500338831986461286211174566188640562338073326685584628277246308115533597456747709550411715558518832471904164421694556105117325926283101462188671410832890817022438821825740816250113973451766541491834874157046643307571699195243678706823471511852169556589447274497854752587596599458099212139043546263"));
        }

}
