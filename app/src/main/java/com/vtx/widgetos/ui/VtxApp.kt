package com.vtx.widgetos.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vtx.widgetos.ui.theme.*

private enum class Tab(val label: String) {
    Home("Home"), Discover("Discover"), Create("Create"), Widgets("My Widgets"), Settings("Settings")
}

@Composable
fun VtxApp() {
    var selected by remember { mutableStateOf(Tab.Home) }
    Scaffold(
        containerColor = Canvas,
        bottomBar = {
            NavigationBar(
                containerColor = Color.White.copy(alpha = 0.88f),
                tonalElevation = 0.dp
            ) {
                Tab.entries.forEach { tab ->
                    val icon = when (tab) {
                        Tab.Home -> Icons.Default.Home
                        Tab.Discover -> Icons.Default.Explore
                        Tab.Create -> Icons.Default.AddCircle
                        Tab.Widgets -> Icons.Default.Widgets
                        Tab.Settings -> Icons.Default.Settings
                    }
                    NavigationBarItem(
                        selected = selected == tab,
                        onClick = { selected = tab },
                        icon = { Icon(icon, contentDescription = tab.label) },
                        label = { Text(tab.label, fontSize = 10.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = TextPrimary,
                            selectedTextColor = TextPrimary,
                            indicatorColor = AccentSoft,
                            unselectedIconColor = TextSecondary,
                            unselectedTextColor = TextSecondary
                        )
                    )
                }
            }
        }
    ) { padding ->
        Box(Modifier.fillMaxSize().padding(padding)) {
            when (selected) {
                Tab.Home -> HomeScreen(onCreate = { selected = Tab.Create }, onDiscover = { selected = Tab.Discover })
                Tab.Discover -> DiscoverScreen()
                Tab.Create -> CreateScreen()
                Tab.Widgets -> MyWidgetsScreen()
                Tab.Settings -> SettingsScreen()
            }
        }
    }
}

@Composable
private fun ScreenFrame(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(
            Brush.verticalGradient(listOf(Color(0xFFF9FAFB), Canvas, Color(0xFFEEF1F4)))
        ).padding(horizontal = 20.dp),
        content = content
    )
}

@Composable
private fun HomeScreen(onCreate: () -> Unit, onDiscover: () -> Unit) {
    ScreenFrame {
        Spacer(Modifier.height(24.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text("VTX WIDGET OS", color = Silver, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
                Spacer(Modifier.height(8.dp))
                Text("Make it yours.", fontSize = 30.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                Text("Your home screen, your design.", color = TextSecondary, fontSize = 13.sp)
            }
            Box(
                Modifier.size(46.dp).background(GlassWhite, CircleShape)
                    .border(1.dp, BorderGlass, CircleShape),
                contentAlignment = Alignment.Center
            ) { Icon(Icons.Default.AutoAwesome, null, tint = Silver) }
        }
        Spacer(Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard("08", "Widget styles", Icons.Default.Widgets, Modifier.weight(1f))
            StatCard("06", "Glass themes", Icons.Default.Palette, Modifier.weight(1f))
        }
        Spacer(Modifier.height(22.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Featured widgets", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
            TextButton(onClick = onDiscover) { Text("Explore", color = TextSecondary) }
        }
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            FeaturedWidget("Crystal Clock", "Minimal time, date and soft silver finish", Icons.Default.Schedule)
            FeaturedWidget("Silver Battery", "A clean battery status card", Icons.Default.BatteryFull)
        }
        Spacer(Modifier.height(18.dp))
        Button(
            onClick = onCreate,
            modifier = Modifier.fillMaxWidth().height(54.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF303943))
        ) {
            Icon(Icons.Default.Add, null)
            Spacer(Modifier.width(8.dp))
            Text("Create new widget", fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun StatCard(value: String, label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier = Modifier) {
    Column(
        modifier.shadow(12.dp, RoundedCornerShape(22.dp))
            .background(Brush.linearGradient(listOf(Color.White, Color(0xFFEFF2F5))), RoundedCornerShape(22.dp))
            .border(1.dp, BorderGlass, RoundedCornerShape(22.dp)).padding(16.dp)
    ) {
        Icon(icon, null, tint = Silver)
        Spacer(Modifier.height(12.dp))
        Text(value, fontSize = 27.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
        Text(label, fontSize = 12.sp, color = TextSecondary)
    }
}

@Composable
private fun FeaturedWidget(title: String, subtitle: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Row(
        Modifier.fillMaxWidth().shadow(8.dp, RoundedCornerShape(20.dp))
            .background(GlassWhite, RoundedCornerShape(20.dp))
            .border(1.dp, BorderGlass, RoundedCornerShape(20.dp)).padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.size(46.dp).background(AccentSoft, RoundedCornerShape(15.dp)), contentAlignment = Alignment.Center) {
            Icon(icon, null, tint = Silver)
        }
        Spacer(Modifier.width(13.dp))
        Column(Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.SemiBold, color = TextPrimary)
            Text(subtitle, fontSize = 12.sp, color = TextSecondary)
        }
        Icon(Icons.Default.ChevronRight, null, tint = Silver)
    }
}

@Composable
private fun DiscoverScreen() {
    ScreenFrame {
        Spacer(Modifier.height(26.dp))
        Text("Discover", fontSize = 28.sp, fontWeight = FontWeight.SemiBold)
        Text("Find a widget style for your space.", color = TextSecondary)
        Spacer(Modifier.height(18.dp))
        var query by remember { mutableStateOf("") }
        OutlinedTextField(
            value = query, onValueChange = { query = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search widgets") },
            leadingIcon = { Icon(Icons.Default.Search, null) },
            shape = RoundedCornerShape(16.dp),
            singleLine = true
        )
        Spacer(Modifier.height(18.dp))
        val items = listOf(
            "Crystal Clock" to Icons.Default.Schedule,
            "Silver Battery" to Icons.Default.BatteryFull,
            "Daily Calendar" to Icons.Default.CalendarMonth,
            "Music Glass" to Icons.Default.MusicNote,
            "Quick Notes" to Icons.Default.Notes,
            "App Shortcuts" to Icons.Default.Apps
        ).filter { it.first.contains(query, ignoreCase = true) }
        LazyVerticalGrid(columns = GridCells.Fixed(2), verticalArrangement = Arrangement.spacedBy(12.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(items) { (name, icon) ->
                Column(
                    Modifier.fillMaxWidth().background(GlassWhite, RoundedCornerShape(20.dp))
                        .border(1.dp, BorderGlass, RoundedCornerShape(20.dp)).padding(16.dp)
                ) {
                    Box(Modifier.size(42.dp).background(AccentSoft, RoundedCornerShape(14.dp)), contentAlignment = Alignment.Center) {
                        Icon(icon, null, tint = Silver)
                    }
                    Spacer(Modifier.height(18.dp))
                    Text(name, fontWeight = FontWeight.SemiBold)
                    Text("Liquid Glass", color = TextSecondary, fontSize = 11.sp)
                }
            }
        }
    }
}

@Composable
private fun CreateScreen() {
    ScreenFrame {
        Spacer(Modifier.height(26.dp))
        Text("Widget Builder", fontSize = 28.sp, fontWeight = FontWeight.SemiBold)
        Text("Compose your own glass widget.", color = TextSecondary)
        Spacer(Modifier.height(24.dp))
        Box(
            Modifier.fillMaxWidth().height(220.dp)
                .background(Brush.linearGradient(listOf(Color.White, Color(0xFFE7EBF0))), RoundedCornerShape(28.dp))
                .border(1.dp, BorderGlass, RoundedCornerShape(28.dp)).padding(22.dp)
        ) {
            Column {
                Text("THURSDAY · NOVEMBER 26", color = Silver, fontSize = 10.sp, letterSpacing = 1.sp)
                Spacer(Modifier.height(10.dp))
                Text("09:41", fontSize = 48.sp, fontWeight = FontWeight.Light, color = TextPrimary)
                Text("A little space, made yours.", color = TextSecondary, fontSize = 12.sp)
                Spacer(Modifier.weight(1f))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.BatteryFull, null, tint = Silver)
                    Text("  Battery status", color = TextSecondary, fontSize = 12.sp)
                    Spacer(Modifier.weight(1f))
                    Text("85%", fontWeight = FontWeight.SemiBold)
                }
            }
        }
        Spacer(Modifier.height(20.dp))
        Text("Start with", fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
        Spacer(Modifier.height(10.dp))
        listOf("Blank canvas", "Clock widget", "Battery widget", "Notes widget").forEach { item ->
            Row(
                Modifier.fillMaxWidth().padding(vertical = 6.dp)
                    .background(GlassWhite, RoundedCornerShape(15.dp))
                    .border(1.dp, BorderGlass, RoundedCornerShape(15.dp)).clickable { }.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.AddBox, null, tint = Silver)
                Spacer(Modifier.width(12.dp))
                Text(item, modifier = Modifier.weight(1f))
                Icon(Icons.Default.ChevronRight, null, tint = Silver)
            }
        }
        Text("Builder preview in Phase 1. Editing and saving arrive in the next phase.", color = TextSecondary, fontSize = 11.sp)
    }
}

@Composable
private fun MyWidgetsScreen() {
    ScreenFrame {
        Spacer(Modifier.height(26.dp))
        Text("My Widgets", fontSize = 28.sp, fontWeight = FontWeight.SemiBold)
        Text("Your saved designs will appear here.", color = TextSecondary)
        Spacer(Modifier.height(24.dp))
        Box(Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.Widgets, null, tint = Silver, modifier = Modifier.size(54.dp))
                Spacer(Modifier.height(12.dp))
                Text("No widgets yet", fontWeight = FontWeight.SemiBold)
                Text("Create your first design to get started.", color = TextSecondary, fontSize = 12.sp)
            }
        }
    }
}

@Composable
private fun SettingsScreen() {
    ScreenFrame {
        Spacer(Modifier.height(26.dp))
        Text("Settings", fontSize = 28.sp, fontWeight = FontWeight.SemiBold)
        Text("Make VTX feel like yours.", color = TextSecondary)
        Spacer(Modifier.height(20.dp))
        listOf(
            "Appearance" to Icons.Default.Palette,
            "Widget preferences" to Icons.Default.Tune,
            "Backup & restore" to Icons.Default.CloudSync,
            "About VTX Widget OS" to Icons.Default.Info
        ).forEach { (label, icon) ->
            Row(
                Modifier.fillMaxWidth().padding(vertical = 5.dp)
                    .background(GlassWhite, RoundedCornerShape(17.dp))
                    .border(1.dp, BorderGlass, RoundedCornerShape(17.dp)).padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(icon, null, tint = Silver)
                Spacer(Modifier.width(14.dp))
                Text(label, modifier = Modifier.weight(1f))
                Icon(Icons.Default.ChevronRight, null, tint = Silver)
            }
        }
        Spacer(Modifier.weight(1f))
        Text("VTX Widget OS  •  Phase 1  •  1.0.0", color = TextSecondary, fontSize = 11.sp, modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 16.dp))
    }
}
