package net.tnemc.market.menu.amountselection;

import net.tnemc.core.TNE;
import net.tnemc.core.common.api.IDFinder;
import net.tnemc.core.menu.icons.Icon;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

/**
 * The New Economy Minecraft Server Plugin
 *
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/ or send a letter to
 * Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
 * Created by Daniel on 11/7/2017.
 */
public class CancelIcon extends Icon {
  public CancelIcon(Integer slot) {
    super(slot, Material.BARRIER, "Cancel");
    this.close = true;
  }

  @Override
  public void onClick(String menu, Player player) {
    TNE.menuManager().setViewerData(IDFinder.getID(player), "action_amount", BigDecimal.ZERO);
    super.onClick(menu, player);
  }
}