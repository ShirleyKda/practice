package screenMobile;

import org.openqa.selenium.By;

public class booking_screen {

    public static By txtUser   = By.id("username");
    public static By txtPass   = By.id("password");

    public static By enterDestino = By.xpath("//*[@text = 'Enter your destination']");
    public static By txtDestino = By.xpath("//*[@text = 'Enter destination']");
    public static By firtOpcionCiudad = By.xpath("*//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]");

    public static By objeto_fecha = By.id("com.booking:id/month_custom_view");
    public static By btn_select_date = By.id("com.booking:id/facet_date_picker_confirm");

    public static By objeto_persona = By.id("com.booking:id/facet_search_box_accommodation_occupancy");
    public static By objeto_sumar_nino = By.xpath("*//android.view.ViewGroup[3]/android.widget.LinearLayout/android.widget.Button[2]");

    public static By label_seleccionar_nino = By.id("android:id/button1");
    public static By btn_aplicar_nino = By.id("com.booking:id/group_config_apply_button");

    public static  By btn_buscar_reserva = By.id("com.booking:id/facet_search_box_cta");
    public static  By label_elegir_ciudad = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView");
    public static  By btn_select_room = By.xpath("//*android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.FrameLayout[2]/android.view.ViewGroup");
    public static  By btn_select_this_option = By.id("com.booking:id/recommended_block_reserve_button");



}
