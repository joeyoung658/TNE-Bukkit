package com.github.tnerevival.core.api;

import com.github.tnerevival.TNE;
import com.github.tnerevival.account.Account;
import com.github.tnerevival.account.Bank;
import com.github.tnerevival.account.IDFinder;
import com.github.tnerevival.core.currency.Currency;
import com.github.tnerevival.core.currency.CurrencyFormatter;
import com.github.tnerevival.core.transaction.TransactionCost;
import com.github.tnerevival.core.transaction.TransactionType;
import com.github.tnerevival.utils.AccountUtils;
import com.github.tnerevival.utils.BankUtils;
import com.github.tnerevival.utils.MISCUtils;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class TNEAPI {

  public TNE plugin;

  public TNEAPI(TNE plugin) {
    this.plugin = plugin;
  }


  /*
   * Account-related Methods
   */

  @Deprecated
  public Boolean accountExists(String username) {
    return AccountUtils.getAccount(getPlayerID(username)) != null;
  }

  @Deprecated
  public void createAccount(String username) {
    AccountUtils.createAccount(getPlayerID(username));
  }

  @Deprecated
  public void createBank(String owner, String world) {
    Bank b = new Bank(getPlayerID(owner), BankUtils.size(world, getPlayerID(owner).toString()));
    Account acc = getAccount(owner);
    acc.setBank(world, b);
    TNE.instance.manager.accounts.put(acc.getUid(), acc);
  }

  @Deprecated
  public void addMember(String owner, String username, String world) {
    Bank b = BankUtils.getBank(IDFinder.getID(owner), world);
    b.addMember(IDFinder.getID(username));
    Account acc = getAccount(owner);
    acc.setBank(world, b);
    TNE.instance.manager.accounts.put(acc.getUid(), acc);
  }

  @Deprecated
  public void removeMember(String owner, String username, String world) {
    Bank b = BankUtils.getBank(IDFinder.getID(owner), world);
    b.removeMember(IDFinder.getID(username));
    Account acc = getAccount(owner);
    acc.setBank(world, b);
    TNE.instance.manager.accounts.put(acc.getUid(), acc);
  }

  @Deprecated
  public boolean bankExists(String owner, String world) {
    return BankUtils.hasBank(getPlayerID(owner), world);
  }

  @Deprecated
  public boolean bankMember(String owner, String username, String world) {
    return BankUtils.bankMember(getPlayerID(owner), getPlayerID(username), world);
  }

  @Deprecated
  public void fundsAdd(String username, Double amount) {
    fundsAdd(username, MISCUtils.getWorld(getPlayerID(username)), amount);
  }

  @Deprecated
  public void fundsAdd(String username, String world, Double amount) {
    AccountUtils.transaction(getPlayerID(username).toString(), null, amount, plugin.manager.currencyManager.get(world), TransactionType.MONEY_GIVE, world);
  }

  @Deprecated
  public void fundsAdd(String username, String world, Double amount, Currency currency) {
    AccountUtils.transaction(getPlayerID(username).toString(), null, amount, currency, TransactionType.MONEY_GIVE, world);
  }

  @Deprecated
  public Boolean fundsHas(String username, Double amount) {
    return fundsHas(username, MISCUtils.getWorld(getPlayerID(username)), amount);
  }

  @Deprecated
  public Boolean fundsHas(String username, String world, Double amount) {
    return AccountUtils.transaction(getPlayerID(username).toString(), null, amount, plugin.manager.currencyManager.get(world), TransactionType.MONEY_INQUIRY, world);
  }

  @Deprecated
  public Boolean fundsHas(String username, String world, Double amount, Currency currency) {
    return AccountUtils.transaction(getPlayerID(username).toString(), null, amount, currency, TransactionType.MONEY_INQUIRY, world);
  }

  @Deprecated
  public void fundsRemove(String username, Double amount) {
    fundsRemove(username, MISCUtils.getWorld(getPlayerID(username)), amount);
  }

  @Deprecated
  public void fundsRemove(String username, String world, Double amount) {
    AccountUtils.transaction(getPlayerID(username).toString(), null, amount, plugin.manager.currencyManager.get(world), TransactionType.MONEY_REMOVE, world);
  }

  @Deprecated
  public void fundsRemove(String username, String world, Double amount, Currency currency) {
    AccountUtils.transaction(getPlayerID(username).toString(), null, amount, currency, TransactionType.MONEY_REMOVE, world);
  }

  @Deprecated
  public Account getAccount(String username) {
    return AccountUtils.getAccount(getPlayerID(username));
  }

  @Deprecated
  public Double getBalance(String username) {
    return AccountUtils.getFunds(getPlayerID(username));
  }

  @Deprecated
  public Double getBalance(String username, String world) {
    return AccountUtils.getFunds(getPlayerID(username), world);
  }

  public Boolean accountExists(OfflinePlayer player) {
    return getAccount(player) != null;
  }

  public void createAccount(OfflinePlayer player) {
    AccountUtils.createAccount(IDFinder.getID(player));
  }

  public void createBank(OfflinePlayer owner, String world) {
    Bank b = new Bank(IDFinder.getID(owner), BankUtils.size(world, IDFinder.getID(owner).toString()));
    Account acc = getAccount(owner);
    acc.setBank(world, b);
    TNE.instance.manager.accounts.put(acc.getUid(), acc);
  }

  public void addMember(OfflinePlayer owner, OfflinePlayer username, String world) {
    Bank b = BankUtils.getBank(IDFinder.getID(owner), world);
    b.addMember(IDFinder.getID(username));
    Account acc = getAccount(owner);
    acc.setBank(world, b);
    TNE.instance.manager.accounts.put(acc.getUid(), acc);
  }

  public void removeMember(OfflinePlayer owner, OfflinePlayer username, String world) {
    Bank b = BankUtils.getBank(IDFinder.getID(owner), world);
    b.removeMember(IDFinder.getID(username));
    Account acc = getAccount(owner);
    acc.setBank(world, b);
    TNE.instance.manager.accounts.put(acc.getUid(), acc);
  }

  public boolean bankExists(OfflinePlayer owner, String world) {
    return BankUtils.hasBank(IDFinder.getID(owner), world);
  }

  public boolean bankMember(OfflinePlayer owner, String username, String world) {
    return BankUtils.bankMember(IDFinder.getID(owner), getPlayerID(username), world);
  }

  public boolean transaction(OfflinePlayer player, OfflinePlayer recipient, String world, TransactionCost cost, TransactionType type) {
    return !(player != null && IDFinder.getID(player) != null) &&
    AccountUtils.transaction(IDFinder.getID(player).toString(), IDFinder.getID(recipient).toString(), cost, type, world);
  }

  public void fundsAdd(OfflinePlayer player, Double amount) {
    fundsAdd(player, MISCUtils.getWorld(getPlayerID(player.getName())), amount);
  }

  public void fundsAdd(OfflinePlayer player, String world, Double amount) {
    if(player != null && IDFinder.getID(player) != null) {
      AccountUtils.transaction(IDFinder.getID(player.getPlayer()).toString(), null, amount, plugin.manager.currencyManager.get(world), TransactionType.MONEY_GIVE, world);
    }
  }

  public void fundsAdd(OfflinePlayer player, String world, Double amount, Currency currency) {
    if(player != null && IDFinder.getID(player) != null) {
      AccountUtils.transaction(IDFinder.getID(player.getPlayer()).toString(), null, amount, currency, TransactionType.MONEY_GIVE, world);
    }
  }

  public Boolean fundsHas(OfflinePlayer player, Double amount) {
    return fundsHas(player, MISCUtils.getWorld(getPlayerID(player.getName())), amount);
  }

  public Boolean fundsHas(OfflinePlayer player, String world, Double amount) {
    return AccountUtils.transaction(IDFinder.getID(player.getPlayer()).toString(), null, amount,  plugin.manager.currencyManager.get(world), TransactionType.MONEY_INQUIRY, world);
  }

  public Boolean fundsHas(OfflinePlayer player, String world, Double amount, Currency currency) {
    return AccountUtils.transaction(IDFinder.getID(player.getPlayer()).toString(), null, amount,  currency, TransactionType.MONEY_INQUIRY, world);
  }

  public void fundsRemove(OfflinePlayer player, Double amount) {
    fundsRemove(player, MISCUtils.getWorld(getPlayerID(player.getName())), amount);
  }

  public void fundsRemove(OfflinePlayer player, String world, Double amount) {
    AccountUtils.transaction(IDFinder.getID(player.getPlayer()).toString(), null, amount, plugin.manager.currencyManager.get(world), TransactionType.MONEY_REMOVE, world);
  }

  public void fundsRemove(OfflinePlayer player, String world, Double amount, Currency currency) {
    AccountUtils.transaction(IDFinder.getID(player.getPlayer()).toString(), null, amount, currency, TransactionType.MONEY_REMOVE, world);
  }

  public Account getAccount(OfflinePlayer player) {
    return AccountUtils.getAccount(IDFinder.getID(player));
  }

  public Double getBalance(OfflinePlayer player) {
    return AccountUtils.getFunds(IDFinder.getID(player));
  }

  public Double getBalance(OfflinePlayer player, String world) {
    return AccountUtils.getFunds(IDFinder.getID(player), world);
  }

  public Double getBalance(OfflinePlayer player, String world, Currency currency) {
    return AccountUtils.getFunds(IDFinder.getID(player), world, currency.getName());
  }

  /*
   * Currency-related Methods.
   */

  public String format(Double amount) {
    return CurrencyFormatter.format(plugin.defaultWorld, amount);
  }

  public String format(String world, Double amount) {
    return CurrencyFormatter.format(world, amount);
  }

  public String getCurrencyName(Boolean major, Boolean singular) {
    return getCurrencyName(major, singular, plugin.defaultWorld);
  }

  public String getCurrencyName(Boolean major, Boolean singular, String world) {
    return (major) ? plugin.manager.currencyManager.get(world).getMajor(singular)  : plugin.manager.currencyManager.get(world).getMinor(singular);
  }

  public Boolean getShorten() {
    return getShorten(plugin.defaultWorld);
  }

  public Boolean getShorten(String world) {
    return plugin.manager.currencyManager.get(world).shorten();
  }

  public UUID getPlayerID(String username) {
    return IDFinder.getID(username);
  }

  /*
   * Configuration-related Methods.
   */
  public String getString(String configuration) {
    return (String)getConfiguration(configuration, TNE.instance.defaultWorld);
  }

  public String getString(String configuration, String world) {
    return (String)getConfiguration(configuration, world, "");
  }

  public String getString(String configuration, String world, UUID uuid) {
    return (String)getConfiguration(configuration, world, uuid.toString());
  }

  public String getString(String configuration, String world, String player) {
    return (String)getConfiguration(configuration, world, player);
  }

  public Boolean getBoolean(String configuration) {
    return (Boolean)getConfiguration(configuration, TNE.instance.defaultWorld);
  }

  public Boolean getBoolean(String configuration, String world) {
    return (Boolean)getConfiguration(configuration, world, "");
  }

  public Boolean getBoolean(String configuration, String world, UUID uuid) {
    return (Boolean)getConfiguration(configuration, world, uuid.toString());
  }

  public Boolean getBoolean(String configuration, String world, String player) {
    return (Boolean)getConfiguration(configuration, world, player);
  }

  public Double getDouble(String configuration) {
    return getDouble(configuration, TNE.instance.defaultWorld);
  }

  public Double getDouble(String configuration, String world) {
    String value = getConfiguration(configuration, world, "").toString();
    return CurrencyFormatter.translateDouble(value, world);
  }

  public Double getDouble(String configuration, String world, UUID uuid) {
    String value = getConfiguration(configuration, world, uuid).toString();
    return CurrencyFormatter.translateDouble(value, world);
  }

  public Double getDouble(String configuration, String world, String player) {
    String value = getConfiguration(configuration, world, player).toString();
    return CurrencyFormatter.translateDouble(value, world);
  }

  public Integer getInteger(String configuration) {
    return (Integer)getConfiguration(configuration, TNE.instance.defaultWorld);
  }

  public Integer getInteger(String configuration, String world) {
    return (Integer)getConfiguration(configuration, world, "");
  }

  public Integer getInteger(String configuration, String world, UUID uuid) {
    return (Integer)getConfiguration(configuration, world, uuid.toString());
  }

  public Integer getInteger(String configuration, String world, String player) {
    return (Integer)getConfiguration(configuration, world, player);
  }

  public boolean hasConfiguration(String configuration) {
    if(configuration.toLowerCase().contains("database")) return false;
    return TNE.configurations.hasConfiguration(configuration);
  }

  public Object getConfiguration(String configuration) {
    return getConfiguration(configuration, TNE.instance.defaultWorld);
  }

  public Object getConfiguration(String configuration, String world) {
    return getConfiguration(configuration, world, "");
  }

  public Object getConfiguration(String configuration, String world, UUID uuid) {
    return getConfiguration(configuration, world, uuid.toString());
  }

  public Object getConfiguration(String configuration, String world, String player) {
    if(configuration.toLowerCase().contains("database")) return "";
    return TNE.configurations.getConfiguration(configuration, world, player);
  }

  public void setConfiguration(String configuration, Object value) {
    if(configuration.toLowerCase().contains("database")) return;
    TNE.configurations.setConfiguration(configuration, value);
  }
}