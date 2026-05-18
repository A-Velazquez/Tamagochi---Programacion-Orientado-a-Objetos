public interface TamagochiBase {



    // Metodos para decir que le falta algo

        // Metodo para indicar que la mascota se queje por hambre
        public abstract void quejarseHambriento();

        // Metodo para indicar que la mascota tiene sueno
        public abstract void cansadoSueno();
        
        // Metodo para indicar que su felicidad esta muy abajo
        public abstract void llorar();

    // Metodos para que la mascota realize una accion

        // Metodo para comer
        public abstract void accionComer();

        // Metodo para dormir
        public abstract void accionDormir();

        // Metodo para subir la felicidad
        public abstract void accionFelicidad();
     

}
