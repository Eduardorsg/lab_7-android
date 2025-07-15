package com.example.lab_7_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab_7_android.ui.theme.Laboratorio7Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio7Theme {
                ModernDigitalCard()
            }
        }
    }
}

@Composable
fun ModernDigitalCard() {
    val context = LocalContext.current

    // Datos del perfil personalizable
    val profileInfo = PersonalProfile(
        fullName = "Eduardo Sequeira",
        jobTitle = "Desarrollador Full Stack",
        expertise = "Especialista en Tecnologías Web & Mobile",
        contactEmail = "eduardo.sequeira@utp.ac.pa",
        phoneNumber = "+507 1234-5678",
        currentLocation = "Panamá, Panamá",
        technicalSkills = listOf(
            "Development" to "🚀",
            "Kotlin & Java" to "☕",
            "Python" to "🐍",
            "FastAPI" to "⚡",
            "APIs" to "🔗",
            "Git" to "📝",
        ),
        socialConnections = listOf(
            DigitalConnection("LinkedIn", Icons.Default.Person, "linkedin.com/in/example"),
            DigitalConnection("GitHub", Icons.Default.Face, "github.com/susgleik"),
            DigitalConnection("Portfolio", Icons.Default.MailOutline, "mi-portfolio.dev")
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF667eea),
                        Color(0xFF764ba2),
                        Color(0xFFf093fb)
                    )
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item { Spacer(modifier = Modifier.height(20.dp)) }

            // Tarjeta principal con avatar
            item {
                HeroProfileSection(profileInfo = profileInfo)
            }

            // Información de contacto moderna
            item {
                ContactInfoSection(profileInfo = profileInfo)
            }

            // Redes sociales con iconos modernos
            item {
                SocialLinksSection(
                    connections = profileInfo.socialConnections,
                    onConnectionClick = { connection ->
                        // TODO: Implementar navegación a redes sociales
                    }
                )
            }

            // Habilidades técnicas
            item {
                TechExpertiseSection(skills = profileInfo.technicalSkills)
            }

            item { Spacer(modifier = Modifier.height(40.dp)) }
        }
    }
}

@Composable
fun HeroProfileSection(profileInfo: PersonalProfile) {
    val infiniteTransition = rememberInfiniteTransition(label = "scale")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.03f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        ), label = "scale_anim"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.95f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
    ) {
        Box {
            // Fondo decorativo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF667eea).copy(alpha = 0.1f),
                                Color(0xFFf093fb).copy(alpha = 0.1f)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Avatar con efecto moderno
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFF667eea),
                                    Color(0xFF764ba2)
                                )
                            )
                        )
                        .padding(6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(128.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.mi_avatar_pic),
                            contentDescription = "Profile Avatar",
                            modifier = Modifier
                                .size(128.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Información principal
                Text(
                    text = profileInfo.fullName,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF2c3e50),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF667eea).copy(alpha = 0.1f)
                    )
                ) {
                    Text(
                        text = profileInfo.jobTitle,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF667eea),
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = profileInfo.expertise,
                    fontSize = 14.sp,
                    color = Color(0xFF7f8c8d),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
fun ContactInfoSection(profileInfo: PersonalProfile) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.95f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    tint = Color(0xFF667eea),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Información de Contacto",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2c3e50)
                )
            }

            ModernContactItem(
                icon = Icons.Default.Email,
                label = "Email",
                value = profileInfo.contactEmail,
                onClick = { /* TODO: Abrir email */ }
            )

            ModernContactItem(
                icon = Icons.Default.Phone,
                label = "Teléfono",
                value = profileInfo.phoneNumber,
                onClick = { /* TODO: Realizar llamada */ }
            )

            ModernContactItem(
                icon = Icons.Default.LocationOn,
                label = "Ubicación",
                value = profileInfo.currentLocation,
                onClick = { /* TODO: Abrir mapa */ }
            )
        }
    }
}

@Composable
fun ModernContactItem(
    icon: ImageVector,
    label: String,
    value: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFf8f9fa)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        Color(0xFF667eea).copy(alpha = 0.1f),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = Color(0xFF667eea),
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = label,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF7f8c8d)
                )
                Text(
                    text = value,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF2c3e50)
                )
            }
        }
    }
}

@Composable
fun SocialLinksSection(
    connections: List<DigitalConnection>,
    onConnectionClick: (DigitalConnection) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.95f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null,
                    tint = Color(0xFF667eea),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Conecta Conmigo",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2c3e50)
                )
            }

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(connections) { connection ->
                    SocialConnectionCard(
                        connection = connection,
                        onClick = { onConnectionClick(connection) }
                    )
                }
            }
        }
    }
}

@Composable
fun SocialConnectionCard(
    connection: DigitalConnection,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .width(140.dp)
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF667eea).copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = connection.platformIcon,
                contentDescription = connection.platformName,
                tint = Color(0xFF667eea),
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = connection.platformName,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2c3e50),
                textAlign = TextAlign.Center
            )

            Text(
                text = connection.profileUrl.take(15) + "...",
                fontSize = 10.sp,
                color = Color(0xFF7f8c8d),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun TechExpertiseSection(skills: List<Pair<String, String>>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.95f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color(0xFF667eea),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Expertise Técnico",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2c3e50)
                )
            }

            // Grid de habilidades
            val skillPairs = skills.chunked(2)
            skillPairs.forEach { skillPair ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    skillPair.forEach { (skill, emoji) ->
                        EnhancedSkillBadge(
                            skillName = skill,
                            emoji = emoji,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    if (skillPair.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun EnhancedSkillBadge(
    skillName: String,
    emoji: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF667eea).copy(alpha = 0.1f)
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            Color(0xFF667eea).copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = emoji,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = skillName,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF2c3e50),
                textAlign = TextAlign.Center
            )
        }
    }
}

// Clases de datos actualizadas
data class PersonalProfile(
    val fullName: String,
    val jobTitle: String,
    val expertise: String,
    val contactEmail: String,
    val phoneNumber: String,
    val currentLocation: String,
    val technicalSkills: List<Pair<String, String>>,
    val socialConnections: List<DigitalConnection>
)

data class DigitalConnection(
    val platformName: String,
    val platformIcon: ImageVector,
    val profileUrl: String
)