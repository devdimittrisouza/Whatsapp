package com.example.whatsappclone.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissao {

    public static boolean validarPermissoes(String[] permissoes, Activity activity, int requestCode){

        if (Build.VERSION.SDK_INT >= 23){

            List<String> listaPermissoes = new ArrayList<>();

            /*Percorre as permissões passadas,
            verificando uma a uma
            * se ja tem a permissao liberada */
            for (String permissao : permissoes){

                Boolean temPerm = ContextCompat.checkSelfPermission(activity, permissao) == PackageManager.PERMISSION_GRANTED;
                if (!temPerm) listaPermissoes.add(permissao);
            }

            //Caso a lista esteja vazia, não é necessário solicitar permissão
            if (listaPermissoes.isEmpty()) return true;

            String[] novasPerms = new String[listaPermissoes.size()];
            listaPermissoes.toArray(novasPerms);

            //solicitando permissão
            ActivityCompat.requestPermissions(activity, novasPerms, requestCode);
        }

        return true;
    }
}
