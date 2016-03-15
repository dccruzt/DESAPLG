package upc.edu.pe.desaplg.helpers;

/**
 * Created by vicva on 1/17/2016.
 */
public class StringsHelper {
    public static String ACCION = "accion";
    public static String RESULTADO = "resultado";
    public static String JUGADOR = "jugador";
    public static String AVATAR = "avatar";

    //Acciones enviadas
    public static String CONECTAR_TV = "conectarTV";
    public static String CONECTAR_JUGADOR = "conectarJugador";
    public static String DESCONECTAR_JUGADOR = "desconectarJugador";
    public static String EMPEZAR_JUEGO = "empezarJuego";
    public static String MOVER_FICHA = "moverFicha";
    public static String EMPEZAR_MOVIMIENTO = "empezarMovimiento";
    public static String SOLTAR_FICHA = "soltarFicha";
    public static String JUGAR = "jugar";
    public static String RETROCEDER_FICHA = "retrocederFicha";
    public static String CAMBIAR_FICHAS = "cambiarFichas";
    public static String PASAR_TURNO = "pasarTurno";
    public static String MOSTRAR_CREDITOS = "mostrarCreditos";

    //Acciones recibidas
    public static String NO_PUEDE_INICIAR = "noPuedeIniciar";
    public static String PUEDE_INICIAR = "puedeIniciar";
    public static String CARGAR_INICIO = "cargarInicio";
    public static String CARGAR_JUEGO = "cargarJuego";
    public static String EMPEZAR_TURNO = "empezarTurno";
    public static String POSICION_FICHA = "posicionFicha";
    public static String VALIDAR_PALABRA = "validarPalabra";
    public static String TERMINAR_TURNO = "terminarTurno";
}