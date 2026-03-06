package com.adventure.service;

import com.adventure.model.GameScene;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameDataService {

    private final Map<String, GameScene> scenes = new HashMap<>();

    public GameDataService() {
        loadScenes();
    }

    public GameScene getScene(String sceneId) {
        return scenes.getOrDefault(sceneId, scenes.get("start"));
    }

    public boolean sceneExists(String sceneId) {
        return scenes.containsKey(sceneId);
    }

    private void loadScenes() {
        // ============ CHAPTER 1: THE AWAKENING ============

        addScene("start", 1, "The Void Awakens",
            "You open your eyes. Total darkness surrounds you. The air tastes of ancient dust and forgotten magic. " +
            "A faint blue glow pulses somewhere ahead — rhythmic, like a heartbeat. Your memory is fragmented: " +
            "shards of a life you can barely grasp. You are the Archivist, keeper of forbidden knowledge. " +
            "Something has gone terribly wrong.\n\n" +
            "A voice echoes from the dark: *'The Seal is broken. The Void hungers. Find the Codex... before it finds you.'*",
            "void",
            List.of(
                choice("s1_c1", "Move toward the blue light", "corridor_of_whispers", null, null, 5),
                choice("s1_c2", "Search your surroundings first", "start_search", null, null, 3),
                choice("s1_c3", "Call out into the darkness", "start_call", null, null, 1)
            ), List.of(), 0, false, null);

        addScene("start_search", 1, "Searching the Dark",
            "You run your hands along cold stone walls. Your fingers find something — a leather satchel. Inside: " +
            "a cracked compass that spins wildly, a vial of glowing azure liquid labeled *'Lucidity'*, and a torn page " +
            "that reads: *'Trust no reflection. The mirrors lie.'*\n\n" +
            "You pocket these items. The blue glow ahead pulses faster.",
            "void",
            List.of(
                choice("ss_c1", "Now head toward the blue light", "corridor_of_whispers", null, null, 5),
                choice("ss_c2", "Read the torn page more carefully", "torn_page_clue", null, null, 2)
            ), List.of("satchel", "compass", "lucidity_vial", "torn_page"), 5, false, null);

        addScene("torn_page_clue", 1, "Words of Warning",
            "The torn page reveals more text as your eyes adjust. The handwriting is your own.\n\n" +
            "*'If you are reading this, the worst has already happened. The Codex of Unmaking was stolen by the Pale Archivist — " +
            "your reflection from the Mirror Realm. He wants to rewrite existence. Do NOT use the Codex to reverse time — " +
            "the paradox will collapse the Archive forever. The only way to seal the Void: find the Three Seals and speak the Word of Binding.'*\n\n" +
            "Your hands tremble. The blue glow pulses insistently.",
            "void",
            List.of(
                choice("tp_c1", "Head toward the blue light — your mission is clear", "corridor_of_whispers", null, null, 8)
            ), List.of(), 5, false, null);

        addScene("start_call", 1, "The Echo",
            "Your voice rings out and is swallowed by the dark. Then — something answers. Not words exactly, " +
            "but a sensation: cold fingers on your spine, a whisper of *'we see you'*.\n\n" +
            "Whatever lurks in the dark knows you are awake. The blue glow flickers urgently. " +
            "You have the feeling you should move. Now.",
            "void",
            List.of(
                choice("sc_c1", "Run toward the light", "corridor_of_whispers_hurried", null, null, 3)
            ), List.of(), 0, false, null);

        addScene("corridor_of_whispers", 1, "The Corridor of Whispers",
            "You step into a long corridor carved from obsidian. Thousands of names are etched into the walls — " +
            "names of the forgotten, erased from history. Floating orbs of pale light drift at eye level. " +
            "Midway down the hall, a figure stands with their back to you. They wear your coat. Your boots.\n\n" +
            "They turn. Your own face stares back — but the eyes are hollow silver. The Pale Archivist.\n\n" +
            "*'You're too late,'* your reflection says, smiling with your mouth. *'The first Seal is already broken.'*",
            "dark_corridor",
            List.of(
                choice("cow_c1", "Demand to know where the Codex is", "pale_confront", null, null, 5),
                choice("cow_c2", "Tackle your reflection immediately", "pale_fight", null, null, 3),
                choice("cow_c3", "Use the Lucidity Vial — if you have it", "pale_vial", "lucidity_vial", null, 10)
            ), List.of(), 0, false, null);

        addScene("corridor_of_whispers_hurried", 1, "The Corridor (Breathless)",
            "You sprint into the obsidian corridor. The pursuing whispers recede as light floods around you. " +
            "You catch your breath — then freeze. Your reflection stands in the hall, watching you with hollow silver eyes. " +
            "*'Running already?'* it asks with your voice. *'This will be easier than I hoped.'*",
            "dark_corridor",
            List.of(
                choice("cowh_c1", "Hold your ground and confront it", "pale_confront", null, null, 5),
                choice("cowh_c2", "Look for another way around", "pale_bypass", null, null, 4)
            ), List.of(), 0, false, null);

        addScene("pale_vial", 1, "The Veil Dissolves",
            "You uncork the Lucidity Vial and drink it in one motion. The corridor SNAPS into sharp clarity. " +
            "The Pale Archivist flickers — it isn't fully solid. It's a projection, a decoy left to slow you down.\n\n" +
            "Behind where it stood, a doorway reveals itself: carved with the symbol of the first Seal — a closed eye. " +
            "You gain a crucial advantage: you know the Pale Archivist fears the Seals.",
            "dark_corridor",
            List.of(
                choice("pv_c1", "Pass through the doorway of the first Seal", "seal_chamber_1", null, null, 15)
            ), List.of(), 15, false, null);

        addScene("pale_confront", 1, "Face to Face",
            "You step forward. *'Tell me where the Codex is.'*\n\n" +
            "The Pale Archivist tilts your head. *'It's already rewriting things. In twelve hours, " +
            "you won't have ever existed. None of us will — except me.'* It gestures at the walls. " +
            "The names begin to disappear, one by one.\n\n" +
            "*'Join me,'* it offers, extending your hand. *'Or spend your last hours chasing Seals that can't save anyone.'*",
            "dark_corridor",
            List.of(
                choice("pc_c1", "Refuse and push past it toward the Seal chamber", "seal_chamber_1", null, null, 10),
                choice("pc_c2", "Ask how the Seals work", "pale_info", null, null, 6),
                choice("pc_c3", "Consider the offer for a moment", "pale_temptation", null, null, 0)
            ), List.of(), 0, false, null);

        addScene("pale_fight", 1, "Fists Against a Mirror",
            "You throw yourself at the reflection. Your fists pass through it like smoke — but the cold is excruciating, " +
            "like plunging your hands into ice water. You stagger back.\n\n" +
            "The Pale Archivist laughs — your laugh, hollowed out. *'You can't fight what you are.'* " +
            "It fades through the wall, leaving a single feather behind. Black, with silver edges.",
            "dark_corridor",
            List.of(
                choice("pf_c1", "Pick up the feather and continue forward", "seal_chamber_1", null, null, 5),
                choice("pf_c2", "Examine the wall it passed through", "hidden_passage", null, null, 8)
            ), List.of("pale_feather"), -10, false, null);

        addScene("pale_bypass", 1, "Shadow Path",
            "You edge along the corridor's shadow side, pressing against the wall. " +
            "Your reflection's gaze follows you, but it doesn't move — it seems bound to the center of the hall. " +
            "You slide past it and find a staircase descending into amber-lit tunnels.",
            "dark_corridor",
            List.of(
                choice("pb_c1", "Descend the amber stairs", "seal_chamber_1", null, null, 8)
            ), List.of(), 5, false, null);

        addScene("pale_info", 1, "Forbidden Knowledge",
            "The Pale Archivist almost looks surprised. *'Smart. The three Seals are fragments of the original Word of Binding — " +
            "the phrase that locked the Void away at the beginning of everything. First Seal: the Chamber of Closed Eyes. " +
            "Second: the Burning Library. Third: the Heart of the Archive itself.'*\n\n" +
            "It smiles. *'I've told you everything because it won't matter. The Codex will finish rewriting before you reach the third.'*",
            "dark_corridor",
            List.of(
                choice("pi_c1", "Run for the first Seal chamber", "seal_chamber_1", null, null, 10)
            ), List.of(), 8, false, null);

        addScene("pale_temptation", 1, "The Weight of Nothing",
            "For just a moment, you consider it. Non-existence. No more weight. No more Archive. No more failures.\n\n" +
            "Then the names on the wall catch your eye — thousands of them. Real people. Real stories. " +
            "Erased already. And you see, near the bottom, a name you recognize.\n\n" +
            "You step back. *'No.'*\n\n" +
            "The Pale Archivist's expression shifts — something almost like respect. *'Then die trying.'* It vanishes.",
            "dark_corridor",
            List.of(
                choice("ptem_c1", "Move to the Seal chamber with renewed resolve", "seal_chamber_1", null, null, 12)
            ), List.of(), 5, false, null);

        addScene("hidden_passage", 1, "Behind the Mirror",
            "The wall isn't solid — it's an illusion. You step through into a small alcove. " +
            "A journal lies open on a pedestal, written in the Pale Archivist's hand (your hand, twisted).\n\n" +
            "*Entry 1: I have separated from the original. I am the better version — unburdened by empathy, by doubt. " +
            "The Codex will give me absolute authorship of reality.*\n\n" +
            "You take the journal. It may be useful.",
            "dark_corridor",
            List.of(
                choice("hp_c1", "Head to the Seal chamber", "seal_chamber_1", null, null, 10)
            ), List.of("pale_journal"), 10, false, null);

        // ============ CHAPTER 1: SEAL 1 ============

        addScene("seal_chamber_1", 1, "The Chamber of Closed Eyes",
            "The chamber is circular, domed with glass that shows not sky but an infinite black ocean above. " +
            "At the center stands a stone plinth bearing a relief of a closed eye — the First Seal. " +
            "Around it, carved into the floor, a message:\n\n" +
            "*'Speak what was lost. Name the first thing stolen by the Void.'*\n\n" +
            "Three pedestals hold objects: a cracked mirror, a withered flower, and an empty hourglass. " +
            "The Seal pulses with energy, waiting.",
            "ancient_temple",
            List.of(
                choice("sc1_c1", "Touch the cracked mirror", "seal_mirror", null, null, 3),
                choice("sc1_c2", "Touch the withered flower", "seal_flower", null, null, 3),
                choice("sc1_c3", "Touch the empty hourglass", "seal_hourglass", null, null, 3),
                choice("sc1_c4", "Speak: 'Memory'", "seal_correct", null, null, 20)
            ), List.of(), 0, false, null);

        addScene("seal_mirror", 1, "The Mirror's Echo",
            "The cracked mirror shows not your reflection but a vast empty library. " +
            "Millions of empty shelves. The realization hits: this is what the Void creates — not destruction, but *emptiness*. " +
            "Stolen stories. Stolen histories.\n\n" +
            "The first thing the Void steals is... Memory.",
            "ancient_temple",
            List.of(
                choice("sm_c1", "Speak the word: 'Memory'", "seal_correct", null, null, 20)
            ), List.of(), 5, false, null);

        addScene("seal_flower", 1, "A Moment Frozen",
            "The flower crumbles at your touch — but doesn't fall. It hangs suspended, mid-decay. " +
            "A single moment preserved, unable to continue.\n\n" +
            "This is what the Void steals first: the ability to *remember* that this moment existed. " +
            "Without memory, even beauty is meaningless.",
            "ancient_temple",
            List.of(
                choice("sf_c1", "Speak: 'Memory'", "seal_correct", null, null, 20)
            ), List.of(), 5, false, null);

        addScene("seal_hourglass", 1, "Empty Hours",
            "You lift the hourglass. Both chambers are empty — not just the sand, but the *glass itself* seems hollow, " +
            "like the concept of time has been taken out of it. It's a perfect metaphor.\n\n" +
            "Without memory, time itself becomes meaningless. The first theft of the Void is Memory.",
            "ancient_temple",
            List.of(
                choice("sh_c1", "Speak: 'Memory'", "seal_correct", null, null, 20)
            ), List.of(), 5, false, null);

        addScene("seal_correct", 1, "The First Seal Answered",
            "The word leaves your lips and the chamber *breathes*. The closed-eye relief opens — not menacingly, " +
            "but with relief, like something long sleeping finally allowed to wake.\n\n" +
            "A fragment of light detaches from the Seal and enters your chest. You feel it: a warmth, a certainty, " +
            "a syllable of the Word of Binding settling into you like a key into a lock it was made for.\n\n" +
            "*One of three,* you think. The floor shakes. Somewhere deep below the Archive, something roars.",
            "ancient_temple",
            List.of(
                choice("sec_c1", "Descend to the Burning Library — the second Seal", "chapter_2_start", null, null, 10)
            ), List.of("seal_fragment_1"), 30, false, null);

        // ============ CHAPTER 2: THE BURNING LIBRARY ============

        addScene("chapter_2_start", 2, "The Descent",
            "You follow winding stairs deep into the Archive's core. The air grows warmer, then hot. " +
            "The walls here are scorched but intact — books burned long ago, leaving only their shadows on stone.\n\n" +
            "You emerge into the Burning Library. It should be ash. Instead, it burns perpetually — " +
            "the same fire, frozen in time, consuming the same pages forever without destroying them. " +
            "Heat blasts your face but nothing actually burns away.\n\n" +
            "At the far end, you can see the Second Seal — but the path is blocked by a creature made of living smoke and ink.",
            "burning_library",
            List.of(
                choice("c2_c1", "Try to find a path around the creature", "library_bypass", null, null, 5),
                choice("c2_c2", "Speak to the creature", "library_speak", null, null, 8),
                choice("c2_c3", "Use the pale feather — if you have it", "library_feather", "pale_feather", null, 15),
                choice("c2_c4", "Read one of the frozen burning books", "library_read", null, null, 6)
            ), List.of(), 0, false, null);

        addScene("library_speak", 2, "The Ink Creature",
            "The creature turns. Its face forms and dissolves constantly — thousands of faces cycling through its smoke.\n\n" +
            "*'Archivist,'* it says with many voices. *'We are what was erased. We are the stories the Void took. " +
            "We cannot leave this fire. We cannot die. We are trapped between existing and not.'*\n\n" +
            "It reaches toward you. *'Give us one thing: be witnessed. Say you see us. Say our existence mattered.'*",
            "burning_library",
            List.of(
                choice("ls_c1", "Say: 'I see you. You mattered. All of you.'", "library_witness", null, null, 25),
                choice("ls_c2", "Say: 'I'm sorry but I need to pass'", "library_refuse", null, null, 0),
                choice("ls_c3", "Ask them to help you reach the Seal", "library_ask_help", null, null, 10)
            ), List.of(), 0, false, null);

        addScene("library_witness", 2, "Witnessed",
            "The words leave you and the creature goes utterly still. Then — one by one — the faces in the smoke solidify, " +
            "smile, and dissolve upward like steam into something beyond the ceiling.\n\n" +
            "Thousands of stories, finally acknowledged. Finally released.\n\n" +
            "The smoke clears. The path to the Second Seal stands open. On the floor where the creature stood, " +
            "words have been burned into stone: *'This is what the Archive was built for.'*",
            "burning_library",
            List.of(
                choice("lw_c1", "Approach the Second Seal", "seal_chamber_2", null, null, 15)
            ), List.of("witness_mark"), 35, false, null);

        addScene("library_refuse", 2, "Cold Words",
            "The creature's faces contort with something between anguish and anger. It doesn't attack — " +
            "it simply weeps smoke and steps aside, diminished.\n\n" +
            "You feel smaller for it. The path opens, but the Archive feels colder.",
            "burning_library",
            List.of(
                choice("lr_c1", "Proceed to the Second Seal", "seal_chamber_2", null, null, 5)
            ), List.of(), -15, false, null);

        addScene("library_ask_help", 2, "Bargain with the Lost",
            "The creature considers. *'We know where the Pale Archivist hides the Codex. In the Heart — " +
            "behind the Third Seal. He believes the Seals protect him. He is wrong.'*\n\n" +
            "It steps aside. *'Go. But remember us when you win.'*",
            "burning_library",
            List.of(
                choice("lah_c1", "Thank them and approach the Second Seal", "seal_chamber_2", null, null, 12)
            ), List.of("codex_location_known"), 20, false, null);

        addScene("library_bypass", 2, "Threading the Flames",
            "You edge along the wall where the fire doesn't quite reach. The heat is oppressive. " +
            "A book falls open as you pass — it shows your life, your history as Archivist. " +
            "Pages are blank where the Void has been at work. You are being erased in real time.",
            "burning_library",
            List.of(
                choice("lb_c1", "Keep moving — reach the Second Seal", "seal_chamber_2", null, null, 8),
                choice("lb_c2", "Try to read what's on the remaining pages", "library_selfread", null, null, 5)
            ), List.of(), 5, false, null);

        addScene("library_feather", 2, "The Pale Key",
            "You hold up the black-silver feather. The creature made of smoke and ink goes rigid. " +
            "A voice emerges from it — not many voices, but one: *'Part of him. Part of the divide. " +
            "He thinks he can erase everything. He cannot erase what was witnessed.'*\n\n" +
            "The creature melts away entirely, leaving the path clear and filling the room with the scent of old books.",
            "burning_library",
            List.of(
                choice("lf_c1", "Approach the Second Seal", "seal_chamber_2", null, null, 10)
            ), List.of(), 20, false, null);

        addScene("library_selfread", 2, "Reading Yourself",
            "The pages show fragments: your mentor teaching you, your first day in the Archive, " +
            "a face you loved — already half-erased. You press your hand to the page. The erasure slows.\n\n" +
            "You understand now: the Seals don't just bind the Void — they restore what was taken. " +
            "Every Seal you repair gives back memory.",
            "burning_library",
            List.of(
                choice("lsr_c1", "Move to the Second Seal with urgency", "seal_chamber_2", null, null, 10)
            ), List.of(), 10, false, null);

        addScene("seal_chamber_2", 2, "The Second Seal",
            "The Second Seal is a wall of fire that doesn't burn — you walk through it and feel only warmth. " +
            "The Seal itself is a relief of a book with no pages. The inscription reads:\n\n" +
            "*'What survives everything? What cannot be destroyed, only hidden?'*\n\n" +
            "Around you, the ruins of a thousand burned stories. The answer is obvious to anyone who has ever loved a book.",
            "burning_library",
            List.of(
                choice("sc2_c1", "Speak: 'Story'", "seal2_wrong", null, null, 0),
                choice("sc2_c2", "Speak: 'Truth'", "seal2_correct", null, null, 25),
                choice("sc2_c3", "Speak: 'Fire'", "seal2_wrong", null, null, 0),
                choice("sc2_c4", "Speak: 'Knowledge'", "seal2_wrong", null, null, 0)
            ), List.of(), 0, false, null);

        addScene("seal2_wrong", 2, "Not That",
            "The Seal pulses coldly. Wrong answer. The fire around you flares hotter. " +
            "You lose ground — whatever was being restored flickers. Try again, quickly.",
            "burning_library",
            List.of(
                choice("s2w_c1", "Speak: 'Truth'", "seal2_correct", null, null, 20)
            ), List.of(), -10, false, null);

        addScene("seal2_correct", 2, "The Second Seal Answered",
            "The fires go cold and silver. The pageless book on the relief fills with light — text appears, " +
            "then glows, then shatters into the same warm fragment that enters your chest.\n\n" +
            "Two syllables of the Word now. You can feel them combining, can almost hear the shape of the full Word. " +
            "One more Seal. The Heart of the Archive.\n\n" +
            "The Burning Library exhales. The eternal flames gutter — and for the first time in ages, go out.",
            "burning_library",
            List.of(
                choice("s2c_c1", "Ascend to the Heart of the Archive", "chapter_3_start", null, null, 15)
            ), List.of("seal_fragment_2"), 40, false, null);

        // ============ CHAPTER 3: THE HEART ============

        addScene("chapter_3_start", 3, "The Heart of All Things",
            "The Heart of the Archive is at the very center of everything — not above, not below, but *inside*. " +
            "You phase through a membrane of solidified time and emerge in a vast spherical chamber.\n\n" +
            "Every wall, floor, and ceiling is covered in writing — the totality of recorded knowledge, " +
            "still being written by invisible hands. At the center of it all floats the Pale Archivist, " +
            "and in his hands: the Codex of Unmaking, blazing with terrible light.\n\n" +
            "He is rewriting. Quickly.",
            "heart_archive",
            List.of(
                choice("c3_c1", "Demand he stop", "final_demand", null, null, 5),
                choice("c3_c2", "Rush him directly", "final_rush", null, null, 3),
                choice("c3_c3", "Speak the two fragments of the Word — begin the Binding", "final_word_start", null, null, 10),
                choice("c3_c4", "Look for the Third Seal first", "find_seal_3", null, null, 8)
            ), List.of(), 0, false, null);

        addScene("final_demand", 3, "Words Against Words",
            "He looks up. His silver eyes are full of something unexpected — grief.\n\n" +
            "*'You don't understand what I'm doing,'* he says. *'I'm not destroying — I'm correcting. " +
            "Every mistake, every tragedy, every needless loss — I'm rewriting them away.'*\n\n" +
            "*'At the cost of everything else,'* you say.\n\n" +
            "*'Yes,'* he agrees simply. *'Everything, for a perfect nothing.'*",
            "heart_archive",
            List.of(
                choice("fd_c1", "Say: 'Imperfect existence is still existence. That matters.'", "final_argument", null, null, 15),
                choice("fd_c2", "Move to the Third Seal while he's talking", "find_seal_3", null, null, 10)
            ), List.of(), 0, false, null);

        addScene("final_argument", 3, "The Archivist's Doubt",
            "The Pale Archivist falters. The Codex flickers in his hands.\n\n" +
            "*'You sound like our mentor,'* he says quietly.\n\n" +
            "*'Maybe she was right,'* you say. *'She always said the Archive wasn't for preserving perfection. " +
            "It was for preserving *everything*.'*\n\n" +
            "He closes his eyes. Just for a moment — just long enough.\n\n" +
            "The Third Seal reveals itself in the floor beneath him, glowing.",
            "heart_archive",
            List.of(
                choice("fa_c1", "Activate the Third Seal immediately", "seal_chamber_3", null, null, 20)
            ), List.of(), 15, false, null);

        addScene("final_rush", 3, "The Collision",
            "You sprint across the chamber. The Pale Archivist looks up and raises his hand — " +
            "words from the Codex fly out like blades. You dodge most of them, but one catches you.\n\n" +
            "A memory vanishes. You don't know what it was — only that it's gone, like a word on the tip of your tongue.\n\n" +
            "But you reach him, and in the struggle, the Codex tumbles — and the Third Seal blazes beneath your feet.",
            "heart_archive",
            List.of(
                choice("fr_c1", "Stand on the Seal and speak", "seal_chamber_3", null, null, 10)
            ), List.of(), -10, false, null);

        addScene("final_word_start", 3, "The Word Rising",
            "You speak the first two fragments. The chamber SHAKES. The writing on the walls begins to glow white-hot. " +
            "The Pale Archivist's head snaps toward you, eyes wide with something like fear.\n\n" +
            "*'Don't,'* he says. *'You don't know what the complete Word will cost you.'*\n\n" +
            "The Third Seal burns in the floor — you can feel it calling for the final fragment.",
            "heart_archive",
            List.of(
                choice("fws_c1", "Find and activate the Third Seal to get the last fragment", "seal_chamber_3", null, null, 15)
            ), List.of(), 10, false, null);

        addScene("find_seal_3", 3, "Searching the Heart",
            "You move along the perimeter of the chamber, eyes scanning the floor. The Pale Archivist watches, wary. " +
            "The writing on the walls speeds up — he's trying to finish before you can act.\n\n" +
            "Then you see it: a spiral carved deep into the floor, partially covered by drifting paper. " +
            "The Third Seal.",
            "heart_archive",
            List.of(
                choice("fs3_c1", "Step into the Seal spiral", "seal_chamber_3", null, null, 10)
            ), List.of(), 5, false, null);

        addScene("seal_chamber_3", 3, "The Third and Final Seal",
            "The Third Seal erupts with light as you step onto it. The inscription forms in the air above:\n\n" +
            "*'What is the Archivist's true purpose?'*\n\n" +
            "Not to collect. Not to preserve. Not even to remember. You know this answer in your bones.",
            "heart_archive",
            List.of(
                choice("sc3_c1", "Speak: 'To witness'", "ending_good", null, null, 30),
                choice("sc3_c2", "Speak: 'To preserve'", "ending_ok", null, null, 15),
                choice("sc3_c3", "Speak: 'To remember'", "ending_ok", null, null, 15),
                choice("sc3_c4", "Speak: 'To protect'", "ending_ok", null, null, 15)
            ), List.of(), 0, false, null);

        // ============ ENDINGS ============

        addScene("ending_good", 3, "The Word of Binding",
            "The three fragments converge. You feel the Word of Binding complete itself inside you — " +
            "not spoken but *embodied*, every syllable a memory, every memory a story witnessed.\n\n" +
            "You speak it aloud.\n\n" +
            "The Void *screams*. The Pale Archivist dissolves — not destroyed, but reintegrated: " +
            "the grief and the rage and the perfectionism folding back into you, where they belong.\n\n" +
            "The Codex of Unmaking closes. The Archive breathes. Every wall fills with writing again — " +
            "all the erased names, restored. The face you loved, whole again.\n\n" +
            "You stand in the Heart of everything, and everything *remembers*.\n\n" +
            "**The Archive endures. The Archivist endures. The story continues.**",
            "heart_archive",
            List.of(), List.of(), 100, true, "victory");

        addScene("ending_ok", 3, "The Binding Holds (Mostly)",
            "The fragments join imperfectly — the Word is close, but not quite right. " +
            "The Void recoils, the Seal slams shut, and the Pale Archivist shatters like glass.\n\n" +
            "The Archive is saved, but not fully healed. Some names stay blank. Some memories remain stolen. " +
            "You sit in the half-restored Heart, knowing you came so close to perfect.\n\n" +
            "It's enough. For now, it's enough.\n\n" +
            "**The Archive endures. Imperfect. Alive.**",
            "heart_archive",
            List.of(), List.of(), 60, true, "victory");
    }

    private void addScene(String id, int chapter, String title, String narrative, String ambiance,
                           List<GameScene.Choice> choices, List<String> items, int score,
                           boolean isEnd, String endType) {
        GameScene scene = new GameScene();
        scene.setId(id);
        scene.setChapter(chapter);
        scene.setTitle(title);
        scene.setNarrative(narrative);
        scene.setAmbiance(ambiance);
        scene.setChoices(choices);
        scene.setItemsAvailable(items);
        scene.setScoreValue(score);
        scene.setEndScene(isEnd);
        scene.setEndType(endType);
        scene.setEffects(new HashMap<>());
        scenes.put(id, scene);
    }

    private GameScene.Choice choice(String id, String text, String nextScene,
                                     String requiresItem, String requiresFlag, int scoreBonus) {
        GameScene.Choice c = new GameScene.Choice();
        c.setId(id);
        c.setText(text);
        c.setNextSceneId(nextScene);
        c.setRequiresItem(requiresItem);
        c.setRequiresFlag(requiresFlag);
        c.setScoreBonus(scoreBonus);
        c.setEffects(new HashMap<>());
        return c;
    }
}
