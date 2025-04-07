This is a simulation of an AI agent that can convert currencies for you. The agent calls an API to get the rates between different currencies.

I chose to implement this functionality since I need to do this pretty musch daily and it seemed like a pretty useful tool to create.
I have already used this instead of google a few times :)

Potential improvements:
1. Switch to a different API that doesn't have call limits. This one allows for 1500 calls per month - enough for personal use, not enough for much else.
2. Better error handling. At the moment some errors are properly handled, some are not handled at all.
3. Tests!
4. Better request parsing. The agent recognises ISO 4217 Three Letter Currency Codes and nothing else. Would be nice if it could understand at least the common currencies in other forms.
5. Even better request parsing! The agent is very strict about the structure that the request should follow. You can't ask it for help in a different way than the one it supports.
6. Functionality for the agent to remember your most common currencies and suggestions based on this information.
7. The API access code should probably not be hard-coded and visible to everyone who wants to see it :)
8. Even better, the agent should allow users to register for the API so everyone could get their own access codes! And use those. At the moment it uses my personal code no matter whose machine it would be launched on.
