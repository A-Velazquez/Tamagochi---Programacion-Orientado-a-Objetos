public interface TamagochiBase {

    // Metodos para decir que le falta algo

        // Metodo para indicar que la mascota se queje por hambre
        public abstract void quejarseHambriento();

        // Metodo para indicar que la mascota tiene sueno
        public abstract void cansadoSueno();
        
        // Metodo para indicar que su felicidad esta muy abajo
        public abstract void llorar();

        // Metodo para indicar que esta muy sucio y quiere un bano
        public abstract void quejarseSuciedad();

        // Metodo para indicar que tienes ganar de ir a defecar
        public abstract void quejarseCagar();

    // Metodos para que la mascota realize una accion

        // Metodo para comer
        public abstract void accionComer() throws Exception;

        // Metodo para dormir
        public abstract void accionDormir() throws Exception;

        // Metodo para subir la felicidad
        public abstract void accionFelicidad() throws Exception;

        // Metodo paara banarse
        public abstract void accionBanarse() throws Exception;

        // Metodo para cagar
        public abstract void accionCagar() throws Exception;
     

}