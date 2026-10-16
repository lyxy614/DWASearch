package com.dwasearch.query.util;

import com.dwasearch.model.DataSnapshot;
import com.dwasearch.model.players.*;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class CoreModule {
    public static void outputPlayers(DataSnapshot dataSnapshot, Writer writer) throws IOException {
        //获取所有player的信息并将所有player添加到单独的ArrayList中，便于排序输出
        List<Player> playerList = new ArrayList<>();

        for (Country country : dataSnapshot.getCountries()){
            playerList.addAll(country.getPlayers());
        }

        //按照国籍升序、lastName升序排序，国籍优先
        Comparator<Player> comparator = Comparator.comparing(Player::getCountry)
                        .thenComparing(Player::getLastName);
        playerList.sort(comparator);

        for (Iterator<Player> iterator = playerList.iterator(); iterator.hasNext();) {
            Player player = iterator.next();
            String content = player.toString() + "\n" + "-----";
            if (iterator.hasNext()){
                content += "\n";
            }
            writer.write(content);
        }
    }

    public static void outputResults(DataSnapshot dataSnapshot, Writer writer) throws IOException {

    }
}
