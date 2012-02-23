package pdm.test.mappe;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class RunnerActivity extends MapActivity {
	/** Called when the activity is first created. */
	MapView mapView;
	MyLocationOverlay myLocationOverlay;
	
	GeoPoint termini;
    GeoPoint piazzadellarepubblica;
    GeoPoint colosseo;
    GeoPoint casaromoloeremo;
	
	RadiusOverlay r1;
	RadiusOverlay r2;
	RadiusOverlay r3;
	RadiusOverlay r4;
	
	PendingIntent mpendingTermini;
	PendingIntent mpendingPiazzaDellaRepubblica;
	PendingIntent mpendingColosseo;
	PendingIntent mpendingCasaDiRomoloERemo;
	
	LocationManager locationManager;
	
	ProxymityBroadcast mProxymityBroadcast= new ProxymityBroadcast();;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mapView = (MapView) findViewById(R.id.map); // Prendo il riferimento alla mapview creata nel file di layout main.xml
		mapView.setClickable(true);					// Abilitazione del pan
		mapView.setBuiltInZoomControls(true);		// Abilitazione dello zoom
		mapView.setSatellite(true);					// Abilitazione della vista satellitare
		
		termini = new GeoPoint (41902022,12500882); 	// Creo un punto georeferenziato con le coordinate di stazione termini
		r1 = new RadiusOverlay(termini,400,Color.BLUE); 
		mapView.getOverlays().add(r1);					// Aggiungo alla mappa l'overlay creato
		
		piazzadellarepubblica = new GeoPoint (41902622,12495482);	 // Creo un punto georeferenziato con le coordinate di piazza della repubblica
		r2 = new RadiusOverlay(piazzadellarepubblica,300,Color.RED); 
		mapView.getOverlays().add(r2);								 // Aggiungo alla mappa l'overlay creato
		
		colosseo = new GeoPoint (41890310,12492410);		// Creo un punto georeferenziato con le coordinate del colosseo
		r3 = new RadiusOverlay(colosseo,500,Color.GREEN); 
		mapView.getOverlays().add(r3);						// Aggiungo alla mappa l'overlay creato
		
		casaromoloeremo = new GeoPoint (41890492,12484823);		// Creo un punto georeferenziato con le coordinate della casa di remo e romolo
		r4 = new RadiusOverlay(casaromoloeremo,300,Color.GRAY); 
		mapView.getOverlays().add(r4);							//Aggiungo alla mappa l'overlay creato
		
		myLocationOverlay = new MyLocationOverlay(this, mapView); 	// Oggetto per la visualizzazione della propria posizione sulla mappa
		myLocationOverlay.runOnFirstFix(new Runnable() {			// Al primo fix della posizione,viene zoomato e animato il centro dello schermo sulla nostra posizione
			public void run() 
			{
				mapView.getController().animateTo(myLocationOverlay.getMyLocation());
				mapView.getController().setZoom(20);			//Livello di zoom sulla propria posizione
			}

		});
	}

	protected void onResume() {
		super.onResume();
		myLocationOverlay.enableMyLocation();	//Attivazione della localizzazione
		locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);		//Si prende il riferimento al LocationManager
		registerReceiver(mProxymityBroadcast, new IntentFilter("pdm.test.mappe"));	
		
		Intent intentTermini = new Intent("pdm.test.mappe");
		intentTermini.putExtra("overlay", 1);
		
		Intent intentPiazzadellarepublica = new Intent("pdm.test.mappe");
		intentPiazzadellarepublica.putExtra("overlay", 2);
		
		Intent intentColosseo = new Intent("pdm.test.mappe");
		intentColosseo.putExtra("overlay", 3);
		
		Intent intentCasaromoloeremo = new Intent("pdm.test.mappe");
		intentCasaromoloeremo.putExtra("overlay", 4);
		
		mpendingTermini = PendingIntent.getBroadcast(this, 1, intentTermini, PendingIntent.FLAG_CANCEL_CURRENT);							//Pending Intent da inviare in broadcast con un parametro extra "overlay" che identifica la zona
		mpendingPiazzaDellaRepubblica = PendingIntent.getBroadcast(this, 2, intentPiazzadellarepublica, PendingIntent.FLAG_CANCEL_CURRENT);
		mpendingColosseo = PendingIntent.getBroadcast(this, 3, intentColosseo, PendingIntent.FLAG_CANCEL_CURRENT);
		mpendingCasaDiRomoloERemo = PendingIntent.getBroadcast(this, 4, intentCasaromoloeremo, PendingIntent.FLAG_CANCEL_CURRENT);
		
		locationManager.addProximityAlert(termini.getLatitudeE6() * 0.000001, termini.getLongitudeE6() * 0.000001, 400, -1, mpendingTermini); //Si imposta un alert di prossimità per "termini"
		locationManager.addProximityAlert(piazzadellarepubblica.getLatitudeE6() * 0.000001, piazzadellarepubblica.getLongitudeE6() * 0.000001, 300, -1, mpendingPiazzaDellaRepubblica); //Si imposta un alert per "PiazzaDellaRepubblica"
		locationManager.addProximityAlert(colosseo.getLatitudeE6() * 0.000001, colosseo.getLongitudeE6() * 0.000001, 500, -1, mpendingColosseo);	//Si imposta un alert per "Colosseo"
		locationManager.addProximityAlert(casaromoloeremo.getLatitudeE6() * 0.000001, casaromoloeremo.getLongitudeE6() * 0.000001, 300, -1, mpendingCasaDiRomoloERemo);		//Si imposta un alert per "CasaDiRomoloERemo"
	}

	protected void onPause() {
		super.onPause();
		myLocationOverlay.disableMyLocation(); //Disattivazione della localizzazione
		locationManager.removeProximityAlert(mpendingTermini);		//Eliminazione del proximity alert nel metodo "onPause()"
		locationManager.removeProximityAlert(mpendingPiazzaDellaRepubblica);
		locationManager.removeProximityAlert(mpendingColosseo);
		locationManager.removeProximityAlert(mpendingCasaDiRomoloERemo);
		unregisterReceiver(mProxymityBroadcast);

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	

	class ProxymityBroadcast extends BroadcastReceiver {

		@Override
		public void onReceive (Context arg0, Intent arg1) {
			Log.d("TAG","Proximity Alert");
			Toast.makeText(getApplicationContext(), "Alert di prossimità", Toast.LENGTH_LONG);
			int area = arg1.getIntExtra("overlay", -1);
			Boolean stoEntrando = arg1.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, true); //Si utilizza la variabile booleana per poter discriminare l'ingresso dall'uscita di una zona
			if (stoEntrando) 
			{
				Toast.makeText(getApplicationContext(), "Benvenuto", Toast.LENGTH_SHORT).show();
				r1.setColor(Color.GREEN);
				r2.setColor(Color.GREEN);
				r3.setColor(Color.GREEN);
				r4.setColor(Color.GREEN);
			}
			else 
			{
				Toast.makeText(getApplicationContext(), "Arrivederci", Toast.LENGTH_SHORT).show();
				r1.setColor(Color.GRAY);
				r2.setColor(Color.GRAY);
				r3.setColor(Color.GRAY);
				r4.setColor(Color.GRAY);
			}
		}
	
	}
	
}
				
				
				
				
				
		/* Individuazione della singola zona allarmata		
				
				
		switch (area) {		//Facendo un controllo sul requestCode associato a ciascun pending intent di ogni zona si individua l'area allarmata
				
				case 1:
					Toast.makeText(getApplicationContext(), "Benvenuto a Termini", Toast.LENGTH_SHORT).show();
					r1.setColor(android.graphics.Color.GREEN);
					break;
				case 2:
					Toast.makeText(getApplicationContext(), "Benvenuto a Piazza della Repubblica", Toast.LENGTH_SHORT).show();
					r2.setColor(android.graphics.Color.GREEN);
					break;
				case 3:
					Toast.makeText(getApplicationContext(), "Benvenuto al Colosseo", Toast.LENGTH_SHORT).show();
					r3.setColor(android.graphics.Color.GREEN);
					break;
				case 4:
					Toast.makeText(getApplicationContext(), "Benvenuto a Casa di Romolo e Remo", Toast.LENGTH_SHORT).show();
					r4.setColor(android.graphics.Color.GREEN);
					break;
				}
			}
			else {
				Toast.makeText(getApplicationContext(), "Arrivederci", Toast.LENGTH_SHORT).show();
				switch (area) {
				case 1:
					r1.setColor(android.graphics.Color.GRAY);
					break;
				case 2:
					r2.setColor(android.graphics.Color.GRAY);
					break;
				case 3:
					r3.setColor(android.graphics.Color.GRAY);
					break;
				case 4:
					r4.setColor(android.graphics.Color.GRAY);
					break;
				}
			}
		}

	}
}*/

/* Così operando, entrando in ogni area questa diventa verde, e questo di imposta tramite il metodo "setColor" se la variabile booleana
   è true, altrimenti, significa che si sta uscendo, e l'area diventa grigia */